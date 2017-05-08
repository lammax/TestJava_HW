package ru.stqa.pft.mantiss.appmanager;

import org.openqa.selenium.By;
import ru.stqa.pft.mantiss.model.UserData;


public class RegistrationHelper extends HelperBase{

   public RegistrationHelper(ApplicationManager app) {
      super(app);
   }


   public void start(UserData user) {
      wd.get(app.getProperty("web.baseUrl") + "/signup_page.php");
      type(By.name("username"), user.getUsername());
      type(By.name("email"), user.getEmail());
      click(By.cssSelector("input[value='Зарегистрироваться']"));
   }

   public void finish(UserData user) {
      wd.get(app.mail().getConfirmationLink(2, user.getEmail()));
      type(By.id("realname"), user.getRealname());
      type(By.name("password"), user.getPassword());
      type(By.name("password_confirm"), user.getPassword());
      click(By.cssSelector("button[type='submit']"));
   }
}
