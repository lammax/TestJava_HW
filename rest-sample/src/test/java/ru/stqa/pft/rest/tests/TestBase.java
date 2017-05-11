package ru.stqa.pft.rest.tests;


import org.openqa.selenium.remote.BrowserType;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.rest.appmanager.ApplicationManager;

import java.io.IOException;


public class TestBase {

   protected static final ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

   @BeforeSuite
   public void setUp() throws Exception {
      app.init();

   }

   @AfterSuite(alwaysRun = true)
   public void tearDown() throws IOException {
      app.stop();
   }

   private boolean isIssueOpen(int issueId) {
      String issueStatus = app.bugify().getIssueById(issueId).get("state_name").getAsString();
      return !(issueStatus.equals("Closed") || issueStatus.equals("Resolved"));
   }

   public void skipIfNotFixed(int issueId) {
      if (isIssueOpen(issueId)) {
         throw new SkipException("Ignored because of issue " + issueId);
      }
   }

}
