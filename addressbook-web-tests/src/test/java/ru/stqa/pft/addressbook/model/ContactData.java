package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

   @Column(name = "fax")
   @Type(type = "text")
   private String fax;

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

   @Transient
   private String allFIO;

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
   @Column(name = "photo")
   @Type(type = "text")
   private String photo;

   @ManyToMany(fetch = FetchType.EAGER)
   @JoinTable(name="address_in_groups",
           joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "group_id")
   )
   private Set<GroupData> groups = new HashSet<GroupData>();

   public String getAllFIO() {
      if (allFIO == null) {
         allFIO = Stream.of(name, lastname).filter((e) -> !e.equals("")).collect(Collectors.joining(" "));
      }
      return allFIO;
   }

   public File getPhoto() {
      return new File(photo);
   }

   public String getAllEmails() {
      if (allEmails == null) {
         allEmails = Stream.of(emaill, email2, email3).filter((e) -> !e.equals("")).collect(Collectors.joining("\n"));
      }
      return allEmails;
   }

   public String getAllPhones() {
      if (allPhones == null) {
         allPhones = Stream.of(homeTel, mobile, workTel, fax).filter((t) -> !t.equals("")).collect(Collectors.joining("\n"));
      }
      return allPhones;
   }

   public Groups getGroups() {
      return new Groups(groups);
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

   public int getId() {
      return id;
   }

   public String getFax() {
      return fax;
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

   public ContactData withAllPhones(String allPhones) {
      this.allPhones = allPhones;
      return this;
   }

   public ContactData withPhoto(File photo) {
      this.photo = photo.getAbsolutePath();
      return this;
   }

   public ContactData withFax(String fax) {
      this.fax = fax;
      return this;
   }

   public ContactData inGroup(GroupData group) {
      this.groups.add(group);
      return this;
   }

   public ContactData withAllFIO(String allFIO) {
      this.allFIO = allFIO;
      return this;
   }

   @Override
   public String toString() {
      return "ContactData{" +
              "id=" + id +
              ", name='" + name + '\'' +
              ", lastname='" + lastname + '\'' +
              ", mobile='" + mobile + '\'' +
              ", homeTel='" + homeTel + '\'' +
              ", workTel='" + workTel + '\'' +
              ", fax='" + fax + '\'' +
              ", emaill='" + emaill + '\'' +
              ", address='" + address + '\'' +
              '}';
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
      if (homeTel != null ? !homeTel.equals(that.homeTel) : that.homeTel != null) return false;
      if (workTel != null ? !workTel.equals(that.workTel) : that.workTel != null) return false;
      if (fax != null ? !fax.equals(that.fax) : that.fax != null) return false;
      if (emaill != null ? !emaill.equals(that.emaill) : that.emaill != null) return false;
      return address != null ? address.equals(that.address) : that.address == null;
   }

   @Override
   public int hashCode() {
      int result = id;
      result = 31 * result + (name != null ? name.hashCode() : 0);
      result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
      result = 31 * result + (mobile != null ? mobile.hashCode() : 0);
      result = 31 * result + (homeTel != null ? homeTel.hashCode() : 0);
      result = 31 * result + (workTel != null ? workTel.hashCode() : 0);
      result = 31 * result + (fax != null ? fax.hashCode() : 0);
      result = 31 * result + (emaill != null ? emaill.hashCode() : 0);
      result = 31 * result + (address != null ? address.hashCode() : 0);
      return result;
   }
}
