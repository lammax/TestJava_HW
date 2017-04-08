package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase {

   @Test
   public void ContactDeletionTests() {
      app.clickAllert();

      if (!(app.getContactHelper().isThereAContact())) {
         app.getContactHelper().createContact(new ContactData("username", "userlastname", "uu", "Home", "54245245245", "user@mailserver.com", "http://somewhere.com", "1985", "address", "Mr", "test1"));
      }

      int before = app.getContactHelper().getContactCount();

      app.getContactHelper().selectContact(before - 1);
      app.getContactHelper().submitContactDeletion();
      app.clickAllert();
      app.getNavigationHelper().gotoHomePage();

      int after = app.getContactHelper().getContactCount();

      Assert.assertEquals(after, before - 1);
   }

}
