package ru.stqa.pft.rest.appmanager;


import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.jayway.restassured.RestAssured;
import org.openqa.selenium.By;
import ru.stqa.pft.rest.model.Issue;

import java.io.IOException;
import java.util.Set;

public class BugifyHelper extends HelperBase {

   public BugifyHelper(ApplicationManager app) {
      super(app);
   }

   public void login() {
      wd.get(app.getProperty("web.baseUrl"));
      type(By.name("username"), app.getProperty("web.demoLogin"));
      type(By.name("password"), app.getProperty("web.demoPassword"));
      click(By.cssSelector("input[type='submit']"));
   }

   public JsonObject getIssueById(int issueId) {
      String json = RestAssured.get(String.format("http://demo.bugify.com/api/issues/%s.json", issueId)).asString();
      JsonObject jsonObject = ((new JsonParser().parse(json).getAsJsonObject()).get("issues")).getAsJsonArray().get(0).getAsJsonObject();
      return jsonObject;
   }

   public Set<Issue> getIssues() throws IOException {
      String json = RestAssured.get("http://demo.bugify.com/api/issues.json").asString();
      JsonElement parsed = new JsonParser().parse(json);
      JsonElement issues = parsed.getAsJsonObject().get("issues");
      return new Gson().fromJson(issues, new TypeToken<Set<Issue>>(){}.getType());
   }

   public int createIssue(Issue newIssue) throws IOException {

      String json = RestAssured.given()
              .parameter("subject", newIssue.getSubject())
              .parameter("description", newIssue.getDescription())
              .post("http://demo.bugify.com/api/issues.json")
              .asString();

      JsonElement parsed = new JsonParser().parse(json);

      return parsed.getAsJsonObject().get("issue_id").getAsInt();

   }

   public boolean isLoginSuccessfull() {
      return isElementPresent(By.cssSelector("a[href='/users/demo/edit"));
   }
}
