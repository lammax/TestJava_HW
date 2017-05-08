package ru.stqa.pft.mantiss.appmanager;

import org.openqa.selenium.By;
import ru.stqa.pft.mantiss.model.UserData;

public class AdministratorHelper extends HelperBase{

   public AdministratorHelper(ApplicationManager app) {
      super(app);
   }

   public void resetPassword(UserData admin, UserData user) {
      //enter as administrator
      wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
      type(By.name("username"), admin.getUsername());
      type(By.name("password"), admin.getPassword());
      click(By.cssSelector("input[value='Войти']"));

      //send reset password request at given user
      click(By.cssSelector("a[href='/mantisbt/manage_overview_page.php']"));
      click(By.cssSelector("a[href='/mantisbt/manage_user_page.php']"));
      click(By.cssSelector(String.format("a[href='manage_user_edit_page.php?user_id=%s']", user.getId())));
      click(By.cssSelector("input[value='Сбросить пароль']"));
   }

   public void finishPasswordChange(UserData user) {
      wd.get(app.mail().getConfirmationLink(1, user.getEmail()));
      type(By.id("password"), user.getPassword());
      type(By.id("password-confirm"), user.getPassword());
      click(By.cssSelector("button[type='submit']"));
   }


}
