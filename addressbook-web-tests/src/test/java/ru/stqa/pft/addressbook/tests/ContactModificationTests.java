package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

    @Test
    public void ContactModificationTests() {
       app.getNavigationHelper().gotoHomePage();
       app.getContactHelper().initContactModification();
       app.getContactHelper().fillContactForm(new ContactData("username0", "userlastname0", "uuu", "Home", "54245245245", "user@mailserver.com", "http://somewhere.com", "1985", "address", "Mr", null), false);
       app.getContactHelper().submitContactModification();
       app.getNavigationHelper().gotoHomePage();
    }

}
