package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.io.File;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDetailedInfoTests extends TestBase {

   @BeforeMethod
   public void ensurePreconditions() {
      app.clickAllert();

      if (app.db().contacts().size() == 0) {
         app.contact().create(new ContactData()
             .withName("username")
             .withLastname("userlastname")
             .withMobile("54245245245")
             .withEmail1("user@mailserver.com")
             .withAddress("address")
         );
         app.goTo().homePage();
      }
   }


   @Test
   public void testContactDetailedInfo() {

      ContactData chosenOne = app.contact().all().iterator().next();

      String contactInfoFromDetailedForm = app.contact().infoFromDetailedForm(chosenOne);

      ContactData contactFromEditForm = app.contact().infoFromEditForm(chosenOne);

      assertThat(contactInfoFromDetailedForm, equalTo(mergeContactData(contactFromEditForm)));

   }

   private String mergeContactData(ContactData contact) {
      return Stream.of(contact.getName() + " " + contact.getLastname(), contact.getAddress(), "M: " + contact.getMobile(), contact.getEmaill())
              .filter((s) -> !s.equals(""))
              .collect(Collectors.joining("\n"));
   }

}
