package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {

   FirefoxDriver wd;

   private SessionHelper sessionHelper;
   private NavigationHelper navigationHelper;
   private GroupHelper groupHelper;
   private ContactHelper contactHelper;


   public void init() {
      wd = new FirefoxDriver();
      wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
      wd.get("http://localhost/addressbook/");
      groupHelper = new GroupHelper(wd);
      navigationHelper = new NavigationHelper(wd);
      contactHelper = new ContactHelper(wd);
      sessionHelper = new SessionHelper(wd);
      sessionHelper.login("admin", "secret");
   }

   public void stop() {
      wd.quit();
   }

   public GroupHelper getGroupHelper() {
      return groupHelper;
   }

   public NavigationHelper getNavigationHelper() {
      return navigationHelper;
   }

   public ContactHelper getContactHelper() {
      return contactHelper;
   }

   public boolean isAlertPresent() {
      try {
         wd.switchTo().alert();
         return true;
      } catch (NoAlertPresentException e) {
         return false;
      }
   }

   public void clickAllert() {
      if (isAlertPresent()) {
         wd.switchTo().alert().accept();
      }
   }

}
