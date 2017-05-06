package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.junit.MatcherAssert.assertThat;

public class ContactGroupTests extends TestBase {

   @Test
   public void testContactAdd2Group() { //ready
      ContactData contact = app.db().contacts().iterator().next();
      //проверить в каких группах контакт состоит
      Groups contactBeforeGroups = contact.getGroups();
      //проверить какие группы вообще есть
      Groups allGroups = app.db().groups();
      //если состоит во всех, добавить новую группу
      Groups diffGroups = allGroups.diff(contactBeforeGroups);
      //добавить контакт в группу
      if (diffGroups.size() == 0) {
         //add new group
         app.goTo().groupPage();
         app.group().create(new GroupData().withName("test" + contact.getId()).withHeader("header" + contact.getId()).withFooter("footer" + contact.getId()));
         app.goTo().homePage();

         //add contact to this group
         allGroups = app.db().groups();
         diffGroups = allGroups.diff(contactBeforeGroups);
         app.contact().put(contact, diffGroups.iterator().next());
      } else {
         contact.inGroup(diffGroups.iterator().next());
      }

      ContactData updatedContact = app.db().contacts().stream().filter((c) -> c.getId() == contact.getId()).iterator().next();

      assertThat(updatedContact.getGroups().size(), equalTo(contactBeforeGroups.size() + 1));

   }

   @Test
   public void testContactDelFromGroup() { //TODO
      ContactData contact = app.db().contacts().iterator().next();
      //проверить в каких группах контакт состоит
      Groups contactBeforeGroups = contact.getGroups();
      //проверить какие группы вообще есть
      Groups allGroups = app.db().groups();
      //если состоит во всех, добавить новую группу
      Groups diffGroups = allGroups.diff(contactBeforeGroups);
      //добавить контакт в группу
      if (diffGroups.size() == 0) {
         //add new group
         app.goTo().groupPage();
         app.group().create(new GroupData().withName("test" + contact.getId()).withHeader("header" + contact.getId()).withFooter("footer" + contact.getId()));
         app.goTo().homePage();

         //add contact to this group
         allGroups = app.db().groups();
         diffGroups = allGroups.diff(contactBeforeGroups);
         app.contact().put(contact, diffGroups.iterator().next());
      } else {
         contact.inGroup(diffGroups.iterator().next());
      }

      ContactData updatedContact = app.db().contacts().stream().filter((c) -> c.getId() == contact.getId()).iterator().next();

      assertThat(updatedContact.getGroups().size(), equalTo(contactBeforeGroups.size() + 1));

   }

}
