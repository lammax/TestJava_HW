package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactHelper extends HelperBase {

   public ContactHelper(FirefoxDriver wd) {
      super(wd);
   }

   public void submitContactCreation() {
       click(By.xpath("//div[@id='content']/form/input[21]"));
   }

   public void fillContactFrom(ContactData contactData) {
      type(By.name("firstname"), contactData.getName());
      type(By.name("lastname"), contactData.getLastname());
      type(By.name("nickname"), contactData.getNick());
      type(By.name("company"), contactData.getCompany());
      type(By.name("title"), contactData.getTitle());
      type(By.name("mobile"), contactData.getMobile());
      type(By.name("email"), contactData.getEmail());
      type(By.name("homepage"), contactData.getHomepage());
      type(By.name("byear"), contactData.getYear());
      type(By.name("address2"), contactData.getAddress());
   }

   public void initContactCreation() {
      click(By.linkText("add new"));
   }

   public void initContactModification() {
      click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
   }

   public void submitContactModification() {
      click(By.xpath("//div[@id='content']/form[1]/input[22]"));
   }

   public void selectContact(String id) {
      if (!wd.findElement(By.id(id)).isSelected()) {
         click(By.id(id));
      }
   }

   public void submitContactDeletion() {
      click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
   }
}
