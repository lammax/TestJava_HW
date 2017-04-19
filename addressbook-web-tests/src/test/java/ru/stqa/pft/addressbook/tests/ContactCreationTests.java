package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        app.goTo().homePage();

        Contacts before = app.contact().all();

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

        assertThat(app.contact().count(), equalTo(before.size() + 1));

        Contacts after = app.contact().all();

        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))
        ));
    }

    @Test
    public void testBadContactCreation() {
        app.goTo().homePage();

        Contacts before = app.contact().all();

        ContactData contact = new ContactData()
                .withName("username456'")
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

        assertThat(app.contact().count(), equalTo(before.size()));

        Contacts after = app.contact().all();

        assertThat(after, equalTo(before));
    }

}
