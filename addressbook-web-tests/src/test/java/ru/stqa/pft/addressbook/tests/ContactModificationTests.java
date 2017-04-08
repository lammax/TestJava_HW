package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.HashSet;
import java.util.List;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() {
       app.getNavigationHelper().gotoHomePage();

       if (!(app.getContactHelper().isThereAContact())) {
          app.getContactHelper().createContact(new ContactData("username", "userlastname", "uu", "Home", "54245245245", "user@mailserver.com", "http://somewhere.com", "1985", "address", "Mr", "test1"));
       }

       List<ContactData> before = app.getContactHelper().getContactList();
       app.getContactHelper().initContactModification(before.size() - 1);

//       ContactData(int id, String name, String lastname, String nick, String company, String mobile, String email, String homepage, String year, String address, String title, String group)
       ContactData contact = new ContactData(
               before.get(before.size() - 1).getId(),
               before.get(before.size() - 1).getName(),
               before.get(before.size() - 1).getLastname(),
               before.get(before.size() - 1).getNick(),
               before.get(before.size() - 1).getCompany(),
               before.get(before.size() - 1).getMobile(),
               before.get(before.size() - 1).getEmail(),
               before.get(before.size() - 1).getHomepage(),
               before.get(before.size() - 1).getYear(),
               before.get(before.size() - 1).getAddress(),
               before.get(before.size() - 1).getTitle(),
               null
       );

       app.getContactHelper().fillContactForm(new ContactData("username777", "userlastname0", "uuu", "Home", "54245245245", "user@mailserver.com", "http://somewhere.com", "1985", "address", "Mr", null), false);
       app.getContactHelper().submitContactModification();
       app.getNavigationHelper().gotoHomePage();

       List<ContactData> after = app.getContactHelper().getContactList();

       Assert.assertEquals(after.size(), before.size());

       after.remove(before.size() - 1);
       after.add(contact);
       Assert.assertEquals(new HashSet<Object>(after), new HashSet<Object>(before));
    }

}