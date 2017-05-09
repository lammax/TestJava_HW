package ru.stqa.pft.mantiss.tests;

import biz.futureware.mantis.rpc.soap.client.IssueData;
import biz.futureware.mantis.rpc.soap.client.MantisConnectPortType;
import biz.futureware.mantis.rpc.soap.client.ObjectRef;
import biz.futureware.mantis.rpc.soap.client.TagData;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.mantiss.model.*;

import javax.mail.MessagingException;
import javax.xml.rpc.ServiceException;
import java.io.IOException;
import java.math.BigInteger;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static org.testng.Assert.assertTrue;

public class ResetPasswordTests extends TestBase {

   @BeforeMethod
   public void startMailServer() {
      app.mail().start();
   }

   @Test
   public void testResetUserPassword() throws IOException, MessagingException {
      long now = System.currentTimeMillis();
      String newUserPassword = String.format("password%s", now);
      Users users = app.db().users();
      UserData user = users.stream().filter((u) -> u.getId() != 1).collect(Collectors.toList()).iterator().next().withPassword(newUserPassword);
      UserData admin = users.stream().filter((u) -> u.getId() == 1).collect(Collectors.toList()).iterator().next().withPassword("root");

      //send reset password request
      app.administrator().resetPassword(admin, user);

      //get letter with confirmation link and chanhe password
      app.administrator().finishPasswordChange(user);

      //ensure new password user login
      assertTrue(app.newSesion().login(user));
   }

   @Test
   public void testBugIssuedResetUserPassword() throws IOException, MessagingException, ServiceException {

      IssuesData issue = app.db().issues().iterator().next();
      MantisConnectPortType mc = app.soap().getMantisConnect();
      Set<Project> projects = app.soap().getProjects();

      long now = System.currentTimeMillis();

      TagData tagData = new TagData();
      tagData.setName("tag" + now);

      BigInteger tagId = mc.mc_tag_add(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"), tagData);
      System.out.println("tagId = " + tagId );

      String[] categories = mc.mc_project_get_categories(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"), BigInteger.valueOf(projects.iterator().next().getId()));
      IssueData issueData = new IssueData();
      issueData.setSummary(issue.getSummary());
      issueData.setDescription("Some desc");
      issueData.setProject(new ObjectRef(BigInteger.valueOf(projects.iterator().next().getId()), projects.iterator().next().getName()));
      issueData.setCategory(categories[0]);
      issueData.setStatus(new ObjectRef(BigInteger.valueOf(issue.getId()), "new"));
      issueData.setTags(new ObjectRef[]{new ObjectRef(tagId, tagData.getName())});

      BigInteger issueId = mc.mc_issue_add(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"), issueData);

      System.out.println(issueId);

      issue = app.db().issues().stream()
             .filter((i) -> {
                IssueData issueWithNeededTag = null;
                try {
                   issueWithNeededTag = mc.mc_issue_get(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"), BigInteger.valueOf(i.getId()));
                } catch (RemoteException e) {
                   e.printStackTrace();
                }

                boolean b = Arrays.stream(issueWithNeededTag.getTags())
                        .map((t) -> {System.out.println( t.getName()); return t;})
                        .filter((t) -> t.getName().equals(tagData.getName()))
                        .collect(Collectors.toList())
                        .size() > 0;
                return b;
             })
            .collect(Collectors.toList())
            .iterator()
            .next();

      if (issue != null) {
         System.out.println("Let's pass this test");
         skipIfNotFixed(issue.getId());
      }


//      long now = System.currentTimeMillis();
      String newUserPassword = String.format("password%s", now);
      Users users = app.db().users();
      UserData user = users.stream().filter((u) -> u.getId() != 1).collect(Collectors.toList()).iterator().next().withPassword(newUserPassword);
      UserData admin = users.stream().filter((u) -> u.getId() == 1).collect(Collectors.toList()).iterator().next().withPassword("root");

      //send reset password request
      app.administrator().resetPassword(admin, user);

      //get letter with confirmation link and chanhe password
      app.administrator().finishPasswordChange(user);

      //ensure new password user login
      assertTrue(app.newSesion().login(user));
   }


   @AfterMethod(alwaysRun = true)
   public void stopMailServer() {
      app.mail().stop();
   }


}
