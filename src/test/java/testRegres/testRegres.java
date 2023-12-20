package testRegres;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.io.File;

public class testRegres {
    File jsonSchema = new File("src/test/java/jsonSchema/getListUser.json");
    JSONObject bodyObj = new JSONObject();

    @Test // GET positive scenario
    public void testGetAllUserList(){
        RestAssured.given().when().get("https://reqres.in/api/users?page=2")
                .then().log().all()
                .assertThat().statusCode(200)
                .assertThat().body("page", Matchers.equalTo(2));
    }

    @Test // GET positive scenario (Validate JSON Schema)
    public void testGetUserValidateJsonSchema(){

        RestAssured.given().when().get("https://reqres.in/api/users?page=2")
                .then().log().all()
                .assertThat().statusCode(200)
                .assertThat().body(JsonSchemaValidator.matchesJsonSchema(jsonSchema));
    }

    @Test // GET negative scenario
    public void testGetNonExistingUser(){
        RestAssured.given().when().get("https://reqres.in/api/users/23")
                .then().log().all()
                .assertThat().statusCode(404);
    }

    @Test // POST positive Scenario
    public void testPostUser(){
        String valueName = "Mickey";
        String valueJob = "Doctor";
        bodyObj.put("name", valueName);
        bodyObj.put("job", valueJob);

        RestAssured.given().header("Content-Type","application/json")
                .contentType(ContentType.JSON).accept(ContentType.JSON)
                .body(bodyObj.toString())
                .when().post("https://reqres.in/api/users")
                .then().log().all()
                .assertThat().statusCode(201);
    }

    @Test // POST positive Scenario
    public void testPostRegisterUser(){
        String valueEmail = "eve.holt@reqres.in";
        String valuePassword = "pistol";
        bodyObj.put("email", valueEmail);
        bodyObj.put("password", valuePassword);

        RestAssured.given().header("Content-Type","application/json")
                .contentType(ContentType.JSON).accept(ContentType.JSON)
                .body(bodyObj.toString())
                .when().post("https://reqres.in/api/register")
                .then().log().all()
                .assertThat().statusCode(200);
    }

    @Test // POST negative Scenario (No password data)
    public void testPostFailedRegisterUser(){
        String valueEmail = "sydney@fife";
        bodyObj.put("email", valueEmail);

        RestAssured.given().header("Content-Type","application/json")
                .contentType(ContentType.JSON).accept(ContentType.JSON)
                .body(bodyObj.toString())
                .when().post("https://reqres.in/api/register")
                .then().log().all()
                .assertThat().statusCode(400);
    }

    @Test
    public void testPutUser() {
        String valueName = "Alvin";
        String valueJob = "Singer";

        bodyObj.put("name", valueName);
        bodyObj.put("job", valueJob);

        RestAssured.given().header("Content-Type", "application/json")
                .contentType(ContentType.JSON).accept(ContentType.JSON)
                .body(bodyObj.toString())
                .when().put("https://reqres.in/api/users/2")
                .then().log().all()
                .assertThat().statusCode(200);
    }

    @Test
    public void testPatchUser() {
        String valueName = "Clary";
        String valueJob = "Painters";

        bodyObj.put("name", valueName);
        bodyObj.put("job", valueJob);

        RestAssured.given().header("Content-Type", "application/json")
                .contentType(ContentType.JSON).accept(ContentType.JSON)
                .body(bodyObj.toString())
                .when().patch("https://reqres.in/api/users/2")
                .then().log().all()
                .assertThat().statusCode(200);
    }

    @Test
    public void testDeleteUser() {

        RestAssured.given().header("Content-Type", "application/json")
                .contentType(ContentType.JSON).accept(ContentType.JSON)
                .when().delete("https://reqres.in/api/users/2")
                .then()
                .assertThat().statusCode(204).log().all();
    }

    @Test // Test NORMAL login scenario
    public void testLoginUser() {
        String valueEmail = "eve.holt@reqres.in";
        String valuePassword = "cityslicka";
        bodyObj.put("email", valueEmail);
        bodyObj.put("password", valuePassword);

        RestAssured.given().header("Content-Type","application/json")
                .contentType(ContentType.JSON).accept(ContentType.JSON)
                .body(bodyObj.toString())
                .when().post("https://reqres.in/api/login")
                .then().log().all()
                .assertThat().statusCode(200);
    }

    @Test // Test INCORRECT EMAIL login scenario (negative scenario [edge case])
    public void testLoginIncorrectUserEmail() {
        String valueEmail = "eve.holta@reqres.in";
        String valuePassword = "cityslicka";
        bodyObj.put("email", valueEmail);
        bodyObj.put("password", valuePassword);

        RestAssured.given().header("Content-Type","application/json")
                .contentType(ContentType.JSON).accept(ContentType.JSON)
                .body(bodyObj.toString())
                .when().post("https://reqres.in/api/login")
                .then().log().all()
                .assertThat().statusCode(400);
    }

    @Test // Test INCORRECT password login scenario (negative scenario [edge case])
    public void testLoginForgotToInputPassword() {
        String valueEmail = "peter@klaven";
        bodyObj.put("email", valueEmail);

        RestAssured.given().header("Content-Type","application/json")
                .contentType(ContentType.JSON).accept(ContentType.JSON)
                .body(bodyObj.toString())
                .when().post("https://reqres.in/api/login")
                .then().log().all()
                .assertThat().statusCode(400);
    }

}
