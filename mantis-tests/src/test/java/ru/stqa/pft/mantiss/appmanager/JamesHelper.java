package ru.stqa.pft.mantiss.appmanager;


import org.apache.commons.net.telnet.TelnetClient;
import ru.stqa.pft.mantiss.model.MailMessage;
import ru.stqa.pft.mantiss.model.UserData;

import javax.mail.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class JamesHelper extends HelperBase {

   private ApplicationManager app;

   private TelnetClient telnet;
   private InputStream in;
   private PrintStream out;

   private Session mailSession;
   private Store store;
   private String mailserver;

   public JamesHelper(ApplicationManager app) {
      super(app);
      this.app = app;
      telnet = new TelnetClient();
      mailSession = Session.getDefaultInstance(System.getProperties());
   }

   public boolean doesUserExist(String name) {
      initTelnetSession();
      write("verify " + name);
      String result = readUntil("exist");
      closeTelnetSession();
      return result.trim().equals("User " + name + " exist");
   }

   public void createUser(UserData user) {
      initTelnetSession();
      System.out.println("createUser initTelnetSession ready");
      System.out.println(user);
      write("adduser " + user.getUsername() + " " + user.getPassword());
      String result = readUntil("User " + user.getUsername() + " added");
      closeTelnetSession();
   }

   private void initTelnetSession() {
      System.out.println("start initTelnetSession");
      mailserver = app.getProperty("mailserver.host");
      int port = Integer.parseInt(app.getProperty("mailserver.port"));
      String login = app.getProperty("mailserver.adminlogin");
      String password = app.getProperty("mailserver.adminpassword");

      try {
         telnet.connect(mailserver, port);
         in = telnet.getInputStream();
         out = new PrintStream( telnet.getOutputStream() );
      } catch (IOException e) {
         e.printStackTrace();
      }

      //First attempt
     /* readUntil("Login id:");
      write("");
      readUntil("Password:");
      write("");*/

      //Second attempt
      readUntil("Login id:");
      write(login);
      readUntil("Password:");
      write(password);

      //read welcome message
//      readUntil("Welcome " + login + ". HELP for a list of commands");
      System.out.println("end initTelnetSession");
   }

   private String readUntil(String pattern) {
      try {
         char lastChar = pattern.charAt(pattern.length() - 1);
         StringBuffer sb = new StringBuffer();
         char ch = (char) in.read();
         while (true) {
            System.out.print(ch);
            sb.append(ch);
            if (ch == lastChar) {
               if (sb.toString().endsWith(pattern)) {
                  return sb.toString();
               }
            }
            ch = (char) in.read();
         }
      } catch (IOException e) {
         e.printStackTrace();
      }
      return null;
   }

   private void write(String value) {
      try {
         out.println(value);
         out.flush();
         System.out.println(value);
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   private void closeTelnetSession() {
      write("quit");
   }

   public void deleteUser(UserData user) {
      initTelnetSession();
      write("deluser " + user.getUsername());
      String result = readUntil("User " + user.getUsername() + " deleted");
      closeTelnetSession();
   }

   public void drainEmail(UserData user) throws MessagingException {
      Folder inbox = openInbox(user);
      for (Message message : inbox.getMessages()) {
         message.setFlag(Flags.Flag.DELETED, true);
      }
   }

   private void closeFolder(Folder folder) throws MessagingException {
      folder.close(true);
      store.close();
   }

   private Folder openInbox(UserData user) throws MessagingException {
      System.out.println("Start openInbox");
      store = mailSession.getStore("pop3");
      store.connect(mailserver, user.getUsername(), user.getPassword());
      Folder folder = store.getDefaultFolder().getFolder("INBOX");
      folder.open(Folder.READ_WRITE);
      System.out.println("Finish openInbox");
      return folder;
   }

   public List<MailMessage> waitForMail(UserData user, long timeout) throws MessagingException {
      long now = System.currentTimeMillis();
      while (System.currentTimeMillis() < now + timeout) {
         System.out.println("Waiting for email for " + user.getUsername());
         List<MailMessage> allMail = getAllMail(user);
         if (allMail.size() > 0) {
            return allMail;
         }
         try {
            Thread.sleep(1000);
         } catch (InterruptedException e) {
            e.printStackTrace();
         }
      }
      throw new Error("No mail :(");
   }

   private List<MailMessage> getAllMail(UserData user) throws MessagingException {
      Folder inbox = openInbox(user);
      System.out.println("getAllMail " + user.getUsername());
      List<MailMessage> messages = Arrays.asList(inbox.getMessages()).stream().map(JamesHelper::toModelMail).collect(Collectors.toList());
      System.out.println("Close inbox. Messages size = " + messages.size());
      closeFolder(inbox);
      return messages;
   }

   private static MailMessage toModelMail(Message m) {
      try {
         System.out.println(m.getAllRecipients()[0].toString());
         System.out.println((String) m.getContent());
         return new MailMessage(m.getAllRecipients()[0].toString(), (String) m.getContent());
      } catch (MessagingException e) {
         e.printStackTrace();
         return null;
      } catch (IOException e) {
         e.printStackTrace();
         return null;
      }
   }

}
