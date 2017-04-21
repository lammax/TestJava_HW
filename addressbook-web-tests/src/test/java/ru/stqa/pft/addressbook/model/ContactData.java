package ru.stqa.pft.addressbook.model;

public class ContactData {
   private int id = Integer.MAX_VALUE;;
   private String name;
   private String lastname;
   private String nick;
   private String company;
   private String mobile;
   private String homeTel;
   private String workTel;
   private String email;
   private String homepage;
   private String year;
   private String address;
   private String title;
   private String group;

   public String getHomeTel() {
      return homeTel;
   }

   public String getWorkTel() {
      return workTel;
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

   public ContactData withHomeTel(String homeTel) {
      this.homeTel = homeTel;
      return this;
   }

   public ContactData withWorkTel(String workTel) {
      this.workTel = workTel;
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

      if (id != that.id) return false;
      return name != null ? name.equals(that.name) : that.name == null;
   }

   @Override
   public int hashCode() {
      int result = id;
      result = 31 * result + (name != null ? name.hashCode() : 0);
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
