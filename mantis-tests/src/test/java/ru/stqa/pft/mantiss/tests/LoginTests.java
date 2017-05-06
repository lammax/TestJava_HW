package ru.stqa.pft.mantiss.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.mantiss.appmanager.HttpSession;

import java.io.IOException;

import static org.testng.Assert.assertTrue;

public class LoginTests extends TestBase {

   @Test
   public void testLogin() throws IOException {
      HttpSession session = app.newSesion();
      assertTrue(session.login("administrator", "root"));
      assertTrue(session.isLoggedInAs("administrator"));
   }

}