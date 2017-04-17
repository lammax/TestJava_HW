package ru.stqa.pft.addressbook.model;

public class ContactData {
   private int id = Integer.MAX_VALUE;;
   private String name;
   private String lastname;
   private String nick;
   private String company;
   private String mobile;
   private String email;
   private String homepage;
   private String year;
   private String address;
   private String title;
   private String group;

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

   public int getId() {
      return id;
   }

   public ContactData withId(int id) {
      this.id = id;
      return this;
   }

   public ContactData withName(String name) {
      this.name = name;
      return this;
   }

   public ContactData withLastname(String lastname) {
      this.lastname = lastname;
      return this;
   }

   public ContactData withNick(String nick) {
      this.nick = nick;
      return this;
   }

   public ContactData withCompany(String company) {
      this.company = company;
      return this;
   }

   public ContactData withMobile(String mobile) {
      this.mobile = mobile;
      return this;
   }

   public ContactData withEmail(String email) {
      this.email = email;
      return this;
   }

   public ContactData withHomepage(String homepage) {
      this.homepage = homepage;
      return this;
   }

   public ContactData withYear(String year) {
      this.year = year;
      return this;
   }

   public ContactData withAddress(String address) {
      this.address = address;
      return this;
   }

   public ContactData withTitle(String title) {
      this.title = title;
      return this;
   }

   public ContactData withGroup(String group) {
      this.group = group;
      return this;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      ContactData that = (ContactData) o;

      if (name != null ? !name.equals(that.name) : that.name != null) return false;
      if (lastname != null ? !lastname.equals(that.lastname) : that.lastname != null) return false;
      if (mobile != null ? !mobile.equals(that.mobile) : that.mobile != null) return false;
      return email != null ? email.equals(that.email) : that.email == null;
   }

   @Override
   public int hashCode() {
      int result = name != null ? name.hashCode() : 0;
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
