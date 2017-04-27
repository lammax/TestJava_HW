package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDetailedInfoTests extends TestBase {

   @Test
   public void testContactDetailedInfo() {

      ContactData chosenOne = app.db().contacts().iterator().next();

      ContactData contactInfoFromDetailedForm = app.contact().infoFromDetailedForm(chosenOne);

      ContactData contactInfoFromDb = new ContactData()
              .withAllFIO(chosenOne.getAllFIO())
              .withAddress(chosenOne.getAddress())
              .withAllEmails(chosenOne.getAllEmails())
              .withAllPhones(chosenOne.getAllPhones());


      assertThat(contactInfoFromDb, equalTo(contactInfoFromDetailedForm));

   }


}
