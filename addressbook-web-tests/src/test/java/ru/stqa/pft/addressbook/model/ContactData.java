package ru.stqa.pft.addressbook.model;

public class ContactData {
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
}
