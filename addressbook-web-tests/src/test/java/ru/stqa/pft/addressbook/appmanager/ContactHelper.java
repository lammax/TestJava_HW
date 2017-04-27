package ru.stqa.pft.addressbook.appmanager;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

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
      type(By.name("mobile"), contactData.getMobile());
      type(By.name("email"), contactData.getEmaill());
      type(By.name("address"), contactData.getAddress());

      if (creation) {
         if (contactData.getGroups().size() > 0) {
            Assert.assertTrue(contactData.getGroups().size() == 1);
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroups().iterator().next().getGroupName());
         }
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
         String lastname = tds.get(1).getText();
         String firstname = tds.get(2).getText();
         String address = tds.get(3).getText();
         String allEmails = tds.get(4).findElements(By.cssSelector("a")).stream().map((we) -> we.getText()).collect(Collectors.joining("\n"));
         String allPhones = tds.get(5).getText();

         contactCache.add(new ContactData()
                 .withId(id)
                 .withName(firstname)
                 .withLastname(lastname)
                 .withAllPhones(allPhones)
                 .withAllEmails(allEmails)
                 .withAddress(address)
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
      String address = getValue(By.name("address"));
      String email1 = getValue(By.name("email"));
      String email2 = getValue(By.name("email2"));
      String email3 = getValue(By.name("email3"));

      wd.navigate().back();

      return new ContactData()
              .withId(contact.getId())
              .withName(firstName)
              .withLastname(lastName)
              .withHomeTel(homeTel)
              .withMobile(mobile)
              .withWorkTel(workTel)
              .withAddress(address)
              .withEmail1(email1)
              .withEmail2(email2)
              .withEmail3(email3);
   }

   private void initContactModificationById(int id) {
      wd.findElement(By.cssSelector("a[href='edit.php?id=" + id + "']")).click();
   }

   public ContactData infoFromDetailedForm(ContactData chosenOne) {

      openContactDetailedFormById(chosenOne);

      //allFIO(firstname, lastname), address, allEmail, allPhones

      /*username 2 userlastname 2
      address2
      M: 542452452452
      user2@mailserver.com*/

      List<String> allDetailedInfo = Arrays.stream(wd.findElement(By.cssSelector("div[id='content']")).getText().split("[\\r\\n]+")).collect(Collectors.toList());

      String allFio = allDetailedInfo.get(0);
      String address = allDetailedInfo.get(1);
      String mobile = allDetailedInfo.get(2).substring(3);
      String email1 = allDetailedInfo.get(3);

      return new ContactData()
              .withAllFIO(allFio)
              .withAddress(address)
              .withAllEmails(email1)
              .withAllPhones(mobile);

   }

   private void openContactDetailedFormById(ContactData chosenOne) {
      wd.findElement(By.cssSelector("a[href='view.php?id=" + chosenOne.getId() + "']")).click();
   }
}
