package ru.stqa.pft.mantiss.model;

import javax.persistence.*;

@Entity
@Table(name = "mantis_user_table")
public class UserData {

   @Id
   @Column(name = "id")
   private int id = Integer.MAX_VALUE;

   @Column(name = "username", length = 65535)
   private String username;

   @Column(name = "realname", length = 65535)
   private String realname;

   @Column(name = "email", length = 65535)
   private String email;

   @Column(name = "password", length = 65535)
   private String password;


   public int getId() {
      return id;
   }
   public String getUsername() {
      return username;
   }
   public String getRealname() {
      return realname;
   }
   public String getEmail() {
      return email;
   }
   public String getPassword() {
      return password;
   }


   public UserData withId(int id) {
      this.id = id;
      return this;
   }

   public UserData withUsername(String username) {
      this.username = username;
      return this;
   }

   public UserData withRealname(String realname) {
      this.realname = realname;
      return this;
   }

   public UserData withEmail(String email) {
      this.email = email;
      return this;
   }

   public UserData withPassword(String password) {
      this.password = password;
      return this;
   }


   @Override
   public String toString() {
      return "UserData{" +
              "id=" + id +
              ", username='" + username + '\'' +
              ", realname='" + realname + '\'' +
              ", email='" + email + '\'' +
              ", password='" + password + '\'' +
              '}';
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      UserData userData = (UserData) o;

      if (id != userData.id) return false;
      if (username != null ? !username.equals(userData.username) : userData.username != null) return false;
      if (realname != null ? !realname.equals(userData.realname) : userData.realname != null) return false;
      if (email != null ? !email.equals(userData.email) : userData.email != null) return false;
      return password != null ? password.equals(userData.password) : userData.password == null;
   }

   @Override
   public int hashCode() {
      int result = id;
      result = 31 * result + (username != null ? username.hashCode() : 0);
      result = 31 * result + (realname != null ? realname.hashCode() : 0);
      result = 31 * result + (email != null ? email.hashCode() : 0);
      result = 31 * result + (password != null ? password.hashCode() : 0);
      return result;
   }
}
