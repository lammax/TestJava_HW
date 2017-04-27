package ru.stqa.pft.addressbook.tests;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

    @BeforeMethod
    public void ensureGroupsPreconditions() {
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test1").withHeader("header1").withFooter("footer1"));
            app.goTo().homePage();
        }
    }

    @DataProvider
    public Iterator<Object[]> validContactsXML() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(new File(app.properties().getProperty("web.contactsXML"))))) {
            String xml = "";
            String line = reader.readLine();

            while (line != null) {
                xml += line;
                line = reader.readLine();
            }

            XStream xStream = new XStream();
            xStream.processAnnotations(ContactData.class);
            List<ContactData> contacts = (List<ContactData>) xStream.fromXML(xml);
            return contacts.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
        }
    }

    @DataProvider
    public Iterator<Object[]> validContactsJSON() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(new File(app.properties().getProperty("web.contactsJSON"))))) {
            String json = "";
            String line = reader.readLine();

            while (line != null) {
                json += line;
                line = reader.readLine();
            }

            Gson gson = new Gson();
            List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>(){}.getType());
            return contacts.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
        }

    }


    @Test(enabled = true, dataProvider = "validContactsJSON")
    public void testContactCreation(ContactData contact) {
        Groups groups = app.db().groups();
        app.goTo().homePage();

        ContactData newContact = contact.inGroup(groups.iterator().next());

        Contacts before = app.db().contacts();

        app.contact().create(newContact);

        assertThat(app.db().contacts().size(), equalTo(before.size() + 1));

        Contacts after = app.db().contacts();

        assertThat(after, equalTo(
                before.withAdded(newContact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))
        ));
    }

    @Test(enabled = false)
    public void testBadContactCreation() {
        app.goTo().homePage();

        Contacts before = app.db().contacts();

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
                .withTitle("Mr")
                .withPhoto(new File(app.properties().getProperty("web.photo")));

        app.contact().create(contact);

        assertThat(app.contact().count(), equalTo(before.size()));

        Contacts after = app.db().contacts();

        assertThat(after, equalTo(before));
    }

}
