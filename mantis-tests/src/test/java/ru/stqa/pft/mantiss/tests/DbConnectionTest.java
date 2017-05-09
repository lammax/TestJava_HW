package ru.stqa.pft.mantiss.tests;

import biz.futureware.mantis.rpc.soap.client.IssueData;
import biz.futureware.mantis.rpc.soap.client.MantisConnectPortType;
import org.testng.annotations.Test;
import ru.stqa.pft.mantiss.model.IssuesData;
import ru.stqa.pft.mantiss.model.UserData;
import ru.stqa.pft.mantiss.model.Users;

import javax.xml.rpc.ServiceException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.sql.*;

public class DbConnectionTest extends TestBase {

   @Test
   public void testDbConnection() throws RemoteException, ServiceException, MalformedURLException {

      for (IssuesData i : app.db().issues()) {
         System.out.println(i);
         System.out.println(isIssueOpen(i.getId()));
      }

   }

   private String isIssueOpen(int issueId) throws MalformedURLException, ServiceException, RemoteException {
      MantisConnectPortType mc = app.soap().getMantisConnect();
      IssueData gotIssueData = mc.mc_issue_get(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"), BigInteger.valueOf(issueId));

      return gotIssueData.getStatus().getName();
   }


}
