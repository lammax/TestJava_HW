package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

   @BeforeMethod
   public void ensurePreconditions() {
      app.goTo().gotoHomePage();

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
                 .withGroup("test1")
         );
      }
   }


    @Test
    public void testContactModification() {

       List<ContactData> before = app.contact().list();
       app.contact().initContactModification(before.size() - 1);
       int index = before.size() - 1;

//       ContactData(int id, String name, String lastname, String nick, String company, String mobile, String email, String homepage, String year, String address, String title, String group)
       ContactData contact = new ContactData()
               .withId(before.get(index).getId())
               .withName(before.get(index).getName())
               .withLastname(before.get(index).getLastname())
               .withNick(before.get(index).getNick())
               .withCompany(before.get(index).getCompany())
               .withMobile(before.get(index).getMobile())
               .withEmail(before.get(index).getEmail())
               .withHomepage(before.get(index).getHomepage())
               .withYear(before.get(index).getYear())
               .withAddress(before.get(index).getAddress())
               .withTitle(before.get(index).getTitle());

       ContactData contactModify = new ContactData()
               .withName("username777")
               .withLastname("userlastname0")
               .withNick("uuu")
               .withCompany("Home")
               .withMobile("54245245245")
               .withEmail("user@mailserver.com")
               .withHomepage("http://somewhere.com")
               .withYear("1985")
               .withAddress("address")
               .withTitle("Mr");
       app.contact().modify(contactModify);
       app.goTo().gotoHomePage();

       List<ContactData> after = app.contact().list();

       Assert.assertEquals(after.size(), before.size());

       after.remove(index);
       after.add(contact);

       Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
       before.sort(byId);
       after.sort(byId);

       Assert.assertEquals(after, before);
    }

}