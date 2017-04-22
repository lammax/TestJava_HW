package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

    @Test(enabled = true)
    public void testContactCreation() {
        app.goTo().homePage();

        Contacts before = app.contact().all();

        File photo = new File("./src/test/resources/seagal.jpg");

        ContactData contact = new ContactData()
            .withName("username456")
            .withLastname("userlastname")
            .withNick("uu")
            .withCompany("Home")
            .withMobile("54245245245")
            .withEmail1("user@mailserver.com")
            .withHomepage("http://somewhere.com")
            .withYear("1985")
            .withAddress("address")
            .withTitle("Mr")
            .withGroup("test1")
            .withPhoto(photo);

        app.contact().create(contact);

        assertThat(app.contact().count(), equalTo(before.size() + 1));

        Contacts after = app.contact().all();

        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))
        ));
    }

    @Test(enabled = false)
    public void testBadContactCreation() {
        app.goTo().homePage();

        Contacts before = app.contact().all();

        ContactData contact = new ContactData()
                .withName("username456'")
                .withLastname("userlastname")
                .withNick("uu")
                .withCompany("Home")
                .withMobile("54245245245")
                .withEmail1("user@mailserver.com")
                .withHomepage("http://somewhere.com")
                .withYear("1985")
                .withAddress("address")
                .withTitle("Mr");

        app.contact().create(contact);

        assertThat(app.contact().count(), equalTo(before.size()));

        Contacts after = app.contact().all();

        assertThat(after, equalTo(before));
    }

}
