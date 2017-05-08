package ru.stqa.pft.mantiss.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantiss.model.MailMessage;
import ru.stqa.pft.mantiss.model.UserData;

import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class RegistrationTests extends TestBase{

   @BeforeMethod
   public void startMailServer() {
      app.mail().start();
   }

   @Test
   public void testRegistration() throws IOException {

      long now = System.currentTimeMillis();
      UserData user = new UserData()
              .withUsername(String.format("user%s", now))
              .withRealname(String.format("user%s", now))
              .withPassword("password")
              .withEmail(String.format("user%S@localhost.localdomain", now));

      app.registration().start(user);
      app.registration().finish(user);
      assertTrue(app.newSesion().login(user));
   }

   @AfterMethod(alwaysRun = true)
   public void stopMailServer() {
      app.mail().stop();
   }


}
