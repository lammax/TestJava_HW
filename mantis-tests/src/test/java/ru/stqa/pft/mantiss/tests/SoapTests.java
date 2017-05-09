package ru.stqa.pft.mantiss.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.mantiss.model.IssuesData;
import ru.stqa.pft.mantiss.model.Project;

import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;

import static org.testng.Assert.assertEquals;


public class SoapTests extends TestBase {

   @Test
   public void testGetProjects() throws MalformedURLException, ServiceException, RemoteException {
      Set<Project> projects = app.soap().getProjects();
      System.out.println(projects.size());
      for (Project p : projects) {
         System.out.println(p.getName());
      }
   }

   @Test
   public void testCreateIssue() throws RemoteException, ServiceException, MalformedURLException {
      Set<Project> projects = app.soap().getProjects();
      IssuesData issue = new IssuesData()
              .withSummary("Test issue")
              .withDescription("Test issue description")
              .withProject(projects.iterator().next());
      IssuesData created = app.soap().addIssue(issue);
      assertEquals(issue.getSummary(), created.getSummary());
   }
}
