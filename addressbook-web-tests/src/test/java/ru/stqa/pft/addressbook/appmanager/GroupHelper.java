package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GroupHelper extends HelperBase {

   public GroupHelper(WebDriver wd) {
      super(wd);
   }

   public void returnToGroupPage() {
      click(By.cssSelector("a[href='group.php']"));
   }

   public void submitGroupCreation() {
      click(By.name("submit"));
   }

   public void fillGroupForm(GroupData groupData) {
      type(By.name("group_name"), groupData.getGroupName());
      type(By.name("group_header"), groupData.getGroupHeader());
      type(By.name("group_footer"), groupData.getGroupFooter());
   }

   public void initGroupCreation() {
      click(By.name("new"));
   }

   public void deleteSelectedGroups() {
      click(By.name("delete"));
   }

   public void selectGroup(int index) {
      wd.findElements(By.name("selected[]")).get(index).click();
   }

   public void selectGroupById(int id) {
      wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
   }

   public void initGroupModification() {
      click(By.name("edit"));
   }

   public void submitGroupModification() {
      click(By.name("update"));
   }

   public void create(GroupData group) {
      initGroupCreation();
      fillGroupForm(group);
      submitGroupCreation();
      groupCache = null;
      returnToGroupPage();
   }

   public void modify(GroupData group) {
      selectGroupById(group.getId());
      initGroupModification();
      fillGroupForm(group);
      submitGroupModification();
      groupCache = null;
      returnToGroupPage();
   }

   public void delete(GroupData group) {
      selectGroupById(group.getId());
      deleteSelectedGroups();
      groupCache = null;
      returnToGroupPage();
   }


   public boolean isThereAGroup() {
      return isElementPresent(By.name("selected[]"));
   }

   private Groups groupCache = null;

   public Groups all() {

      if (groupCache != null) {
         return new Groups(groupCache);
      }

      groupCache = new Groups();
      List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));

      for(WebElement e : elements) {
         int id = Integer.parseInt(e.findElement(By.tagName("input")).getAttribute("value"));
         String name = e.getText();
         groupCache.add(new GroupData().withId(id).withName(name));
      }

      return new Groups(groupCache);

   }

   public int count() {
      return wd.findElements(By.name("selected[]")).size();
   }

}
