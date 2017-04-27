package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import ru.stqa.pft.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

//   -c 3 -f contacts.json -d json

   @Parameter(names = "-c", description = "Contact count")
   public int count;

   @Parameter(names = "-f", description = "Target file")
   public String file;

   @Parameter(names = "-d", description = "Data format")
   public String format;


   public static void main(String[] args) throws IOException {
      ContactDataGenerator generator = new ContactDataGenerator();
      JCommander jCommander = new JCommander(generator);
      try {
         jCommander.parse(args);
      } catch (ParameterException e) {
         jCommander.usage();
         return;
      }
      generator.run();
   }

   private void run() throws IOException {
      List<ContactData> contacts = generateContacts(count);
      if (format.equals("csv")) {
         saveAsCsv(contacts, new File(file));
      } else if (format.equals("xml")){
         saveAsXml(contacts, new File(file));
      } else if (format.equals("json")){
         saveAsJson(contacts, new File(file));
      } else {
         System.out.println("Unrecognized format " + format);
      }
   }

   private void saveAsJson(List<ContactData> contacts, File file) throws IOException {

      Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
      String json = gson.toJson(contacts);

      try (Writer writer = new FileWriter(file)) {
         writer.write(json);
      }

   }

   private void saveAsXml(List<ContactData> contacts, File file) throws IOException {

      XStream xstream = new XStream();
      xstream.processAnnotations(ContactData.class);
      String xml = xstream.toXML(contacts);

      try (Writer writer = new FileWriter(file)) {
         writer.write(xml);
      }

   }

   private void saveAsCsv(List<ContactData> contacts, File file) throws IOException {

      try (Writer writer = new FileWriter(file)) {
         for (ContactData contact : contacts) {
            writer.write(String.format("%s;%s;%s\n", contact.getName(), contact.getLastname(), contact.getNick()));
         }
      }

   }

   private List<ContactData> generateContacts(int count) {

      List<ContactData> contacts = new ArrayList<>();

      for (int i = 1; i < count + 1; i++) {
         contacts.add(new ContactData()
                 .withName(String.format("username %s", i))
                 .withLastname(String.format("userlastname %s", i))
                 .withNick(String.format("mick%s", i))
                 .withCompany(String.format("Company%s", i))
                 .withMobile(String.format("54245245245%s", i))
                 .withEmail1(String.format("user%s@mailserver.com", i))
                 .withHomepage(String.format("http://somewhere%s.com",i))
                 .withYear(String.format("198%s", i))
                 .withAddress(String.format("address%s", i))
                 .withTitle("Mr")
                 .withGroup(String.format("test %s", i))
                 .withPhoto(new File("seagal.jpg"))
         );
      }

      return contacts;
   }

}
