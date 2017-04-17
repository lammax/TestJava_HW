package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Set;

public class ContactModificationTests extends TestBase {

   @BeforeMethod
   public void ensurePreconditions() {
      app.goTo().gotoHomePage();

      if (app.contact().all().size() == 0) {
         app.contact().create(new ContactData()
                 .withName("username")
                 .withLastname("userlastname")
                 .withNick("uu")
                 .withCompany("Home")
                 .withMobile("54245245245")
                 .withEmail("user@mailserver.com")
                 .withHomepage("http://somewhere.com")
                 .withYear("1985")
                 .withAddress("address")
                 .withTitle("Mr")
                 .withGroup("test1")
         );
      }
   }


    @Test
    public void testContactModification() {

       Set<ContactData> before = app.contact().all();
       ContactData modifiedContact = before.iterator().next();
       app.contact().initContactModification(modifiedContact);

       ContactData contact = new ContactData()
               .withId(modifiedContact.getId())
               .withName(modifiedContact.getName())
               .withLastname("userlastname0")
               .withNick("uuu")
               .withCompany("Home")
               .withMobile("54245245245")
               .withEmail("user@mailserver.com")
               .withHomepage("http://somewhere.com")
               .withYear("1985")
               .withAddress("address")
               .withTitle("Mr");

       app.contact().modify(contact);
       app.goTo().gotoHomePage();

       Set<ContactData> after = app.contact().all();

       Assert.assertEquals(after.size(), before.size());

       after.remove(contact);
       after.add(modifiedContact);

       Assert.assertEquals(after, before);
    }

}