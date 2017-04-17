package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Set;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        app.goTo().gotoHomePage();

        Set<ContactData> before = app.contact().all();

        ContactData contact = new ContactData()
            .withName("username456")
            .withLastname("userlastname")
            .withNick("uu")
            .withCompany("Home")
            .withMobile("54245245245")
            .withEmail("user@mailserver.com")
            .withHomepage("http://somewhere.com")
            .withYear("1985")
            .withAddress("address")
            .withTitle("Mr")
            .withGroup("test1");

        app.contact().create(contact);

        Set<ContactData> after = app.contact().all();

        Assert.assertEquals(after.size(), before.size() + 1);

        contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());

        before.add(contact);
        Assert.assertEquals(before, after);
    }

}
