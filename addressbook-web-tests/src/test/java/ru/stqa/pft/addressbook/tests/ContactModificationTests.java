package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

    @Test
    public void ContactModificationTests() {
       app.getNavigationHelper().gotoHomePage();

       if (!(app.getContactHelper().isThereAContact())) {
          app.getContactHelper().createContact(new ContactData("username", "userlastname", "uu", "Home", "54245245245", "user@mailserver.com", "http://somewhere.com", "1985", "address", "Mr", "test1"));
       }

       int before = app.getContactHelper().getContactCount();

       app.getContactHelper().initContactModification(before - 1);
       app.getContactHelper().fillContactForm(new ContactData("username777", "userlastname0", "uuu", "Home", "54245245245", "user@mailserver.com", "http://somewhere.com", "1985", "address", "Mr", null), false);
       app.getContactHelper().submitContactModification();
       app.getNavigationHelper().gotoHomePage();

       int after = app.getContactHelper().getContactCount();

       Assert.assertEquals(after, before);
    }

}
