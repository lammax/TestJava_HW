package ru.stqa.pft.mantiss.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.mantiss.model.UserData;
import ru.stqa.pft.mantiss.model.Users;

import java.io.IOException;
import java.util.stream.Collectors;

import static org.testng.Assert.assertTrue;

public class ResetPasswordTests extends TestBase {

   @BeforeMethod
   public void startMailServer() {
      app.mail().start();
   }

   @Test
   public void testResetUserPassword() throws IOException {
      long now = System.currentTimeMillis();
      String newUserPassword = String.format("password%s", now);
      Users users = app.db().users();
      UserData user = users.stream().filter((u) -> u.getId() != 1).collect(Collectors.toList()).iterator().next().withPassword(newUserPassword);
      UserData admin = users.stream().filter((u) -> u.getId() == 1).collect(Collectors.toList()).iterator().next().withPassword("root");

      //send reset password request
      app.administrator().resetPassword(admin, user);

      //get letter with confirmation link and chanhe password
      app.administrator().finishPasswordChange(user);

      //ensure new password user login
      assertTrue(app.newSesion().login(user));
   }



   @AfterMethod(alwaysRun = true)
   public void stopMailServer() {
      app.mail().stop();
   }


}
