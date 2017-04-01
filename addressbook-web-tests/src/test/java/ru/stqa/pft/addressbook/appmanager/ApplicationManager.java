package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {

   WebDriver wd;
   String browser = BrowserType.FIREFOX;

   private SessionHelper sessionHelper;
   private NavigationHelper navigationHelper;
   private GroupHelper groupHelper;
   private ContactHelper contactHelper;

   public ApplicationManager(String browser) {
      this.browser = browser;
   }


   public void init() {

      if (browser.equals(BrowserType.FIREFOX)) {
         wd = new FirefoxDriver();
      } else if (browser.equals(BrowserType.CHROME)) {
         wd = new ChromeDriver();
      } else if (browser.equals(BrowserType.IE)) {
         wd = new InternetExplorerDriver();
      }

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
