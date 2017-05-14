package ru.stqa.pft.addressbook.rf;

import org.openqa.selenium.remote.BrowserType;
import ru.stqa.pft.addressbook.appmanager.ApplicationManager;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.IOException;

public class AddressbookKeywords {

   public static final String ROBOT_LIBRARY_SCOPE = "GLOBAL";

   private ApplicationManager app;

   public void initApplicationManager() throws IOException {
      app = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));
      app.init();
   }

   public void stopApplicationManager() {
      app.stop();
      app = null;
   }

   public int getGroupCount() {
      app.goTo().groupPage();
      return app.group().count();

   }

   public void createGroup(String name, String header, String footer) {
      System.out.println(">>> name = " + name);
      System.out.println(">>> header = " + header);
      System.out.println(">>> footer = " + footer);
      app.goTo().groupPage();
      app.group().create(new GroupData().withName(name).withHeader(header).withFooter(footer));
   }

}
