package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase {

   @Test
   public void ContactDeletionTests() {
      app.clickAllert();
      app.getContactHelper().selectContact("4");
      app.getContactHelper().submitContactDeletion();
      app.clickAllert();
      app.getNavigationHelper().gotoHomePage();
   }

}
