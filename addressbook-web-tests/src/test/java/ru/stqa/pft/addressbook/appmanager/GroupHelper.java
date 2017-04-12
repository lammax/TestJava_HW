package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;

public class GroupHelper extends HelperBase {

   public GroupHelper(WebDriver wd) {
      super(wd);
   }

   public void returnToGroupPage() {
      click(By.linkText("group page"));
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
      /*click(
        (By.cssSelector("input[name='selected[]']:nth-child(" + (index+1) + ")"))
      );*/
   }

   public void initGroupModification() {
      click(By.name("edit"));
   }

   public void submitGroupModification() {
      click(By.name("update"));
   }

   public void createGroup(GroupData group) {
      initGroupCreation();
      fillGroupForm(group);
      submitGroupCreation();
      returnToGroupPage();
   }

   public void modifyGroup(int index, GroupData group) {
      selectGroup(index);
      initGroupModification();
      fillGroupForm(group);
      submitGroupModification();
      returnToGroupPage();
   }

   public boolean isThereAGroup() {
      return isElementPresent(By.name("selected[]"));
   }

   public List<GroupData> getGroupList() {

      List<GroupData> groups = new ArrayList<GroupData>();
      List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));

      for(WebElement e : elements) {
         int id = Integer.parseInt(e.findElement(By.tagName("input")).getAttribute("value"));
         String name = e.getText();
         GroupData group = new GroupData(id, name, null, null);
         groups.add(group);
      }

      return groups;

   }

}
