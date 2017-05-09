package ru.stqa.pft.mantiss.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.mantiss.appmanager.HttpSession;
import ru.stqa.pft.mantiss.model.UserData;

import java.io.IOException;

import static org.testng.Assert.assertTrue;

public class LoginTests extends TestBase {

   @Test
   public void testLogin() throws IOException {
      HttpSession session = app.newSesion();
      UserData user = new UserData()
              .withUsername(app.getProperty("web.adminLogin"))
              .withPassword(app.getProperty("web.adminPassword"));
      assertTrue(session.login(user));
      assertTrue(session.isLoggedInAs(user));
   }

}