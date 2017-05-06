package ru.stqa.pft.mantiss.appmanager;

import org.openqa.selenium.WebDriver;

/**
 * Created by user on 06.05.2017.
 */
public class RegistrationHelper {
   private final ApplicationManager app;
   private WebDriver wd;

   public RegistrationHelper(ApplicationManager app) {
      this.app = app;
      wd = app.getDriver();
   }


   public void start(String username, String email) {
      wd.get(app.getProperty("web.baseUrl") + "/signup_page.php");
   }
}
