package ru.stqa.pft.addressbook.appmanager;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.*;

public class ContactHelper extends HelperBase {

   public ContactHelper(WebDriver wd) {
      super(wd);
   }

   public void submitContactCreation() {
       click(By.xpath("//div[@id='content']/form/input[21]"));
   }

   public void fillContactForm(ContactData contactData, boolean creation) {
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

      if (creation) {
         new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
      } else {
         Assert.assertFalse(isElementPresent(By.name("new_group")));
      }

   }

   public void initContactCreation() {
      click(By.linkText("add new"));
   }

   public void initContactModification(int index) {
      wd.findElements(By.cssSelector("img[title='Edit']")).get(index).click();
   }

   public void submitContactModification() {
      click(By.xpath("//div[@id='content']/form[1]/input[22]"));
   }

   public void selectContact(int index) {
      wd.findElements(By.name("selected[]")).get(index).click();
      /*click(
        (By.cssSelector("input[name='selected[]']:nth-child(" + (index+1) + ")"))
      );*/
   }

   public void submitContactDeletion() {
      click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
   }

   public boolean isThereAContact() {
      return isElementPresent(By.name("selected[]"));
   }

   public void createContact(ContactData contact) {
      initContactCreation();
      fillContactForm(contact, true);
      submitContactCreation();
   }

   public int getContactCount() {
      return wd.findElements(By.name("selected[]")).size();
   }

   public List<ContactData> getContactList() {

      List<ContactData> contacts = new ArrayList<ContactData>();

      List<WebElement> elements = wd.findElements(By.cssSelector("tr[name='entry']"));

      for(WebElement e : elements) {
//         ContactData(String name, String lastname, String nick, String company, String mobile, String email, String homepage, String year, String address, String title, String group)
         List<WebElement> tds = e.findElements(By.cssSelector("td"));
         String lastname = tds.get(1).getAttribute("textContent");
         String firstname = tds.get(2).getAttribute("textContent");
         String email = tds.get(4).findElement(By.cssSelector("a")).getAttribute("textContent");
         String phone = tds.get(5).getAttribute("textContent");

         ContactData contact = new ContactData(firstname, lastname, null, null, phone, email, null, null,null, null, null);
         contacts.add(contact);
      }

      return contacts;


   }
}
