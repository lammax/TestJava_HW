package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTests extends TestBase {


   @BeforeMethod
   public void ensurePreconditions() {
      app.clickAllert();

      if (app.contact().all().size() == 0) {
         app.contact().create(new ContactData()
                 .withName("username")
                 .withLastname("userlastname")
                 .withNick("uu")
                 .withCompany("Home")
                 .withHomeTel("1111111111")
                 .withMobile("2222222222222")
                 .withWorkTel("3333333333")
                 .withEmail("user@mailserver.com")
                 .withHomepage("http://somewhere.com")
                 .withYear("1985")
                 .withAddress("address")
                 .withTitle("Mr")
                 .withGroup("test1"));
      }
   }

   @Test
   public void testContactPhones() {
      app.goTo().homePage();
      ContactData contact = app.contact().all().iterator().next();
      ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

      assertThat(contact.getHomeTel(), equalTo(cleaned(contactInfoFromEditForm.getHomeTel())));
      assertThat(contact.getMobile(), equalTo(cleaned(contactInfoFromEditForm.getMobile())));
      assertThat(contact.getWorkTel(), equalTo(cleaned(contactInfoFromEditForm.getWorkTel())));
   }

   public String cleaned(String phone) {
      return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
   }

}
