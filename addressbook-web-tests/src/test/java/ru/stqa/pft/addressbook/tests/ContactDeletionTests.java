package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionTests extends TestBase {

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
                 .withGroup("test1")
                 .withPhoto(new File(app.properties().getProperty("web.photo")))
         );
      }
   }

   @Test
   public void testContactDeletion() {
      Contacts before = app.db().contacts();
      ContactData deletedContact = before.iterator().next();

      app.contact().delete(deletedContact);
      app.clickAllert();
      app.goTo().homePage();

      assertThat(app.db().contacts().size(), equalTo(before.size() - 1));

      Contacts after = app.db().contacts();

      assertThat(after, equalTo(before.without(deletedContact)));
   }

}
