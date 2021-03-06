package ru.stqa.pft.addressbook.tests;


import org.openqa.selenium.remote.BrowserType;
import org.testng.ITestContext;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.appmanager.ApplicationManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.junit.MatcherAssert.assertThat;

@Listeners(MyTestListener.class)
public class TestBase {

   protected final static Logger logger = LoggerFactory.getLogger(TestBase.class);

   protected static final ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

   @BeforeSuite
   public void setUp(ITestContext testContext) throws Exception {
      app.init();
      testContext.setAttribute("app", app);
   }

   @AfterSuite(alwaysRun = true)
   public void tearDown() {
      app.stop();
   }

   @BeforeMethod
   public void logTestStart(Method m, Object[] p) {
      logger.info("Start " + m.getName() + " with parameters " + Arrays.asList(p));
   }

   @AfterMethod(alwaysRun = true)
   public void logTestStop(Method method) {
      logger.info("Stop " + method.getName());
   }

   public void verifyGroupListInUI() {

      if (Boolean.getBoolean("verifyUI")) {

         Groups dbGroups = app.db().groups();
         Groups uiGroups = app.group().all();

         assertThat(
                 uiGroups,
                 equalTo(
                         dbGroups
                                 .stream()
                                 .map((g) -> new GroupData().withId(g.getId()).withName(g.getGroupName()))
                                 .collect(Collectors.toSet())
                 )
         );
      }

   }

   public void verifyContactListInUI() {

      if (Boolean.getBoolean("verifyUI")) {

         Contacts dbContacts = app.db().contacts();
         Contacts uiContacts = app.contact().all();

         assertThat(
                 uiContacts,
                 equalTo(
                         dbContacts
                                 .stream()
                                 .map((c) -> new ContactData()
                                         .withId(c.getId())
                                         .withName(c.getName())
                                         .withLastname(c.getLastname())
                                         .withAllEmails(
                                                 Stream.of(c.getEmaill(), c.getEmail2(), c.getEmail3())
                                                         .filter((e) -> !e.equals(""))
                                                         .collect(Collectors.joining("\n"))
                                         )
                                         .withAllPhones(
                                                 Stream.of(c.getHomeTel(), c.getMobile(), c.getWorkTel())
                                                         .filter((t) -> !t.equals(""))
                                                         .collect(Collectors.joining("\n"))
                                         )
                                         .withAddress(c.getAddress())
                                 )
                                 .collect(Collectors.toSet())
                 )
         );
      }

   }


}
