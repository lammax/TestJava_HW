package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Set;

public class ContactDeletionTests extends TestBase {

   @BeforeMethod
   public void ensurePreconditions() {
      app.clickAllert();

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
                 .withGroup("test1"));
      }
   }

   @Test
   public void testContactDeletion() {
      Set<ContactData> before = app.contact().all();
      ContactData deletedContact = before.iterator().next();

      app.contact().delete(deletedContact);
      app.clickAllert();
      app.goTo().gotoHomePage();

      Set<ContactData> after = app.contact().all();

      Assert.assertEquals(after.size(), before.size() - 1);

      before.remove(deletedContact);
      Assert.assertEquals(before, after);
   }

}
