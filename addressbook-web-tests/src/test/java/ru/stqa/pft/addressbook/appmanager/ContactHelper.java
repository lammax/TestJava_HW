package ru.stqa.pft.addressbook.appmanager;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.List;

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
      type(By.name("home"), contactData.getHomeTel());
      type(By.name("mobile"), contactData.getMobile());
      type(By.name("work"), contactData.getWorkTel());
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

   public void initContactModification(ContactData contact) {
      initContactModificationById(contact.getId());
   }

   public void submitContactModification() {
      click(By.xpath("//div[@id='content']/form[1]/input[22]"));
   }

   public void selectContact(int index) {
      wd.findElements(By.name("selected[]")).get(index).click();
   }

   private void selectContactById(int id) {
      wd.findElement(By.cssSelector("input[value='" + id+ "']")).click();
   }


   public void submitContactDeletion() {
      click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
   }

   public boolean isThereAContact() {
      return isElementPresent(By.name("selected[]"));
   }

   public void create(ContactData contact) {
      initContactCreation();
      fillContactForm(contact, true);
      submitContactCreation();
      contactCache = null;
   }

   public void modify(ContactData contactModify) {
      fillContactForm(contactModify, false);
      submitContactModification();
      contactCache = null;
   }

   public void delete(ContactData contact) {
      selectContactById(contact.getId());
      submitContactDeletion();
      contactCache = null;
   }

   private Contacts contactCache = null;

   public Contacts all() {

      if (contactCache != null) {
         return new Contacts(contactCache);
      }

      contactCache = new Contacts();

      List<WebElement> elements = wd.findElements(By.cssSelector("tr[name='entry']"));

      for(WebElement e : elements) {
//         ContactData(int id, String name, String lastname, String nick, String company, String mobile, String email, String homepage, String year, String address, String title, String group)
         List<WebElement> tds = e.findElements(By.cssSelector("td"));
         int id = Integer.parseInt(tds.get(0).findElement(By.tagName("input")).getAttribute("value"));
         String lastname = tds.get(1).getAttribute("textContent");
         String firstname = tds.get(2).getAttribute("textContent");
         String email = tds.get(4).findElement(By.cssSelector("a")).getAttribute("textContent");
         String[] phones = tds.get(5).getText().split("\n");

         contactCache.add(new ContactData()
                 .withId(id)
                 .withName(firstname)
                 .withLastname(lastname)
                 .withHomeTel(phones[0])
                 .withMobile(phones[1])
                 .withWorkTel(phones[2])
                 .withEmail(email)
         );
      }

      return new Contacts(contactCache);
   }

   public int count() {
      return wd.findElements(By.name("selected[]")).size();
   }

   public ContactData infoFromEditForm(ContactData contact) {
      initContactModificationById(contact.getId());
      String firstName = getValue(By.name("firstname"));
      String lastName = getValue(By.name("lastname"));
      String homeTel = getValue(By.name("home"));
      String mobile = getValue(By.name("mobile"));
      String workTel = getValue(By.name("work"));

      wd.navigate().back();

      return new ContactData().withId(contact.getId()).withName(firstName).withLastname(lastName).withHomeTel(homeTel).withMobile(mobile).withWorkTel(workTel);
   }

   private void initContactModificationById(int id) {
      wd.findElement(By.cssSelector("a[href='edit.php?id=" + id + "']")).click();
   }
}
