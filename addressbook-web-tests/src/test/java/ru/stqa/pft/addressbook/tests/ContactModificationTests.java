package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase {

   @BeforeMethod
   public void ensurePreconditions() {
      app.clickAllert();
      if (app.db().contacts().size() == 0) {
         app.contact().create(new ContactData()
                 .withName("username")
                 .withLastname("userlastname")
                 .withNick("uu")
                 .withCompany("Home")
                 .withMobile("54245245245")
                 .withEmail1("user@mailserver.com")
                 .withHomepage("http://somewhere.com")
                 .withYear("1985")
                 .withAddress("address")
                 .withTitle("Mr")
//                 .withGroup("test1")
                 .withPhoto(new File(app.properties().getProperty("web.photo")))
         );
      }
   }


    @Test
    public void testContactModification() {

       Contacts before = app.db().contacts();
       ContactData modifiedContact = before.iterator().next();
       app.contact().initContactModification(modifiedContact);

       ContactData contact = new ContactData()
               .withId(modifiedContact.getId())
               .withName(modifiedContact.getName())
               .withLastname("userlastname0")
               .withNick("uuu")
               .withCompany("Home")
               .withMobile("54245245245")
               .withEmail1("user@mailserver.com")
               .withHomepage("http://somewhere.com")
               .withYear("1985")
               .withAddress("address")
               .withTitle("Mr")
               .withPhoto(new File(app.properties().getProperty("web.photo")));

       app.contact().modify(contact);
       app.goTo().homePage();

       assertThat(app.db().contacts().size(), equalTo(before.size()));

       Contacts after = app.db().contacts();

       assertThat(before, equalTo(after.without(contact).withAdded(modifiedContact)));

       verifyContactListInUI();
    }

}