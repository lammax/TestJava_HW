package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class HelperBase {

   protected WebDriver wd;

   public HelperBase(WebDriver wd) {
      this.wd = wd;
   }

   protected void click(By locator) {
      wd.findElement(locator).click();
   }

   protected void type(By locator, String text) {
      if (text != null) {
         String existingText = wd.findElement(locator).getAttribute("value");
         if (! text.equals(existingText)) {
            click(locator);
            wd.findElement(locator).clear();
            wd.findElement(locator).sendKeys(text);
         }
      }
   }


   protected boolean isElementPresent(By locator) {
      try {
         wd.findElement(locator);
         return true;
      } catch (NoSuchElementException ex) {
         return false;
      }
   }
}