package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {

   @BeforeMethod
   public void ensurePreconditions() {
      app.clickAllert();

      if (app.contact().list().size() == 0) {
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
      List<ContactData> before = app.contact().list();
      int index = before.size() - 1;

      app.contact().delete(index);
      app.clickAllert();
      app.goTo().gotoHomePage();

      List<ContactData> after = app.contact().list();

      Assert.assertEquals(after.size(), index);

      before.remove(index);
      Assert.assertEquals(before, after);
   }

}
