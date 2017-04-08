package ru.stqa.pft.addressbook.model;

public class ContactData {
   private int id;
   private final String name;
   private final String lastname;
   private final String nick;
   private final String company;
   private final String mobile;
   private final String email;
   private final String homepage;
   private final String year;
   private final String address;
   private final String title;
   private String group;

   public ContactData(String name, String lastname, String nick, String company, String mobile, String email, String homepage, String year, String address, String title, String group) {
      this.id = 0;
      this.name = name;
      this.lastname = lastname;
      this.nick = nick;
      this.company = company;
      this.mobile = mobile;
      this.email = email;
      this.homepage = homepage;
      this.year = year;
      this.address = address;
      this.title = title;
      this.group = group;
   }

   public ContactData(int id, String name, String lastname, String nick, String company, String mobile, String email, String homepage, String year, String address, String title, String group) {
      this.id = id;
      this.name = name;
      this.lastname = lastname;
      this.nick = nick;
      this.company = company;
      this.mobile = mobile;
      this.email = email;
      this.homepage = homepage;
      this.year = year;
      this.address = address;
      this.title = title;
      this.group = group;
   }

   public String getName() {
      return name;
   }

   public String getLastname() {
      return lastname;
   }

   public String getNick() {
      return nick;
   }

   public String getCompany() {
      return company;
   }

   public String getMobile() {
      return mobile;
   }

   public String getEmail() {
      return email;
   }

   public String getHomepage() {
      return homepage;
   }

   public String getYear() {
      return year;
   }

   public String getAddress() {
      return address;
   }

   public String getTitle() {
      return title;
   }

   public String getGroup() {
      return group;
   }

   public void setId(int id) {
      this.id = id;
   }

   public int getId() {
      return id;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      ContactData that = (ContactData) o;

      if (id != that.id) return false;
      if (name != null ? !name.equals(that.name) : that.name != null) return false;
      if (lastname != null ? !lastname.equals(that.lastname) : that.lastname != null) return false;
      if (mobile != null ? !mobile.equals(that.mobile) : that.mobile != null) return false;
      return email != null ? email.equals(that.email) : that.email == null;
   }

   @Override
   public int hashCode() {
      int result = id;
      result = 31 * result + (name != null ? name.hashCode() : 0);
      result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
      result = 31 * result + (mobile != null ? mobile.hashCode() : 0);
      result = 31 * result + (email != null ? email.hashCode() : 0);
      return result;
   }

   @Override
   public String toString() {
      return "ContactData{" +
              "id=" + id +
              ", name='" + name + '\'' +
              ", lastname='" + lastname + '\'' +
              ", mobile='" + mobile + '\'' +
              ", email='" + email + '\'' +
              '}';
   }
}
