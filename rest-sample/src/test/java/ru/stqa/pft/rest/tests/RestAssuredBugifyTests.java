package ru.stqa.pft.rest.tests;

import com.jayway.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.Assert.assertTrue;

public class RestAssuredBugifyTests extends TestBase {

   @BeforeClass
   public void init() {
      RestAssured.authentication = RestAssured.basic("LSGjeU4yP1X493ud1hNniA==", "");
   }

   @Test
   public void testBugifyLogin() throws IOException {

      skipIfNotFixed(app.bugify().getIssues().iterator().next().getId());

      app.bugify().login();

      assertTrue(app.bugify().isLoginSuccessfull());
   }


}
