package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;

@XStreamAlias("contact")
@Entity
@Table(name = "addressbook")
public class ContactData {
   @XStreamOmitField
   @Id
   @Column(name = "id")
   private int id = Integer.MAX_VALUE;;

   @Expose
   @Column(name = "firstname")
   private String name;

   @Expose
   @Column(name = "lastname")
   private String lastname;

   @Expose
   @Column(name = "nickname", length = 65535)
//   @Type(type = "text")
   private String nick;

   @Expose
   @Column(name = "company")
   private String company;

   @Expose
   @Column(name = "mobile")
   @Type(type = "text")
   private String mobile;

   @Column(name = "home")
   @Type(type = "text")
   private String homeTel;

   @Column(name = "work")
   @Type(type = "text")
   private String workTel;

   @Transient
   private String allPhones;

   @Expose
   @Column(name = "email")
   @Type(type = "text")
   private String emaill;

   @Column(name = "email2")
   @Type(type = "text")
   private String email2;

   @Column(name = "email3")
   @Type(type = "text")
   private String email3;

   @Transient
   private String allEmails;

   @Expose
   @Column(name = "homepage")
   @Type(type = "text")
   private String homepage;

   @Expose
   @Column(name = "byear", length = 65535)
//   @Type(type = "text")
   private String year;

   @Expose
   @Column(name = "address")
   @Type(type = "text")
   private String address;

   @Expose
   @Column(name = "title", length = 65535)
//   @Type(type = "text")
   private String title;

   @Expose
   @Transient
   private String group;

   @Column(name = "photo")
   @Type(type = "text")
   private String photo;

   public File getPhoto() {
      return new File(photo);
   }

   public String getAllEmails() {
      return allEmails;
   }

   public String getAllPhones() {
      return allPhones;
   }

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

   public String getEmaill() {
      return emaill;
   }

   public String getEmail2() {
      return email2;
   }

   public String getEmail3() {
      return email3;
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

   public ContactData withEmail1(String email1) {
      this.emaill = email1;
      return this;
   }

   public ContactData withEmail2(String email2) {
      this.email2 = email2;
      return this;
   }

   public ContactData withEmail3(String email3) {
      this.email3 = email3;
      return this;
   }

   public ContactData withAllEmails(String allEmails) {
      this.allEmails = allEmails;
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

   public ContactData withAllPhones(String allPhones) {
      this.allPhones = allPhones;
      return this;
   }

   public ContactData withPhoto(File photo) {
      this.photo = photo.getPath();
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
              ", email='" + emaill + '\'' +
              '}';
   }

}
