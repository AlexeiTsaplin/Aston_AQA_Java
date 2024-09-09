package lesson17;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;

import static io.restassured.RestAssured.given;

public class testApi {

    @BeforeTest
    public void setup() {
        RestAssured.baseURI = "https://postman-echo.com";
    }

    @Test
    public void postmanEchoGetStatus200() {
        given().when().get().then().assertThat().statusCode(200);
    }

    @Test
    public void postmanEchoPostRawStatus200() {
        String requestBody = "{\"name\": \"John\", \"surname\": \"Dow\"}";

        Response response = given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post("/post")
                .then()
                .statusCode(200)
                .extract()
                .response();

        String responseBody = response.getBody().asString();
        System.out.println("Response Body: " + responseBody);

        given()
                .body(responseBody)
                .then()
                .body("json.name", equalTo("John"))
                .body("json.surname", equalTo("Dow"));
    }

    @Test
    public void postmanEchoPostFormDataStatus200() {
        given()
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .formParam("name", "John")
                .formParam("surname", "Dow")
                .when()
                .post("/post")
                .then()
                .statusCode(200)
                .body("form.name", equalTo("John"))
                .body("form.surname", equalTo("Dow"));
    }

    @Test
    public void postmanEchoPutStatus200() {
        Response response = RestAssured.given()
                .contentType("application/json")
                .body("{\"name\":\"John\"}")
                .when()
                .put("/put");

        String jsonResponse = response.jsonPath().getString("json.name");
        Assert.assertEquals(jsonResponse, "John", "Имя не соответствует ожидаемому значению");
    }

    @Test
    public void postmanEchoPatchStatus200(){
        String requestBody = "{\"name\":\"John\"}";

        Response response = RestAssured.given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .patch("/patch");

        Assert.assertEquals(response.getStatusCode(), 200, "Статус ответа должен быть 200");

        System.out.println("Ответ от сервера: " + response.asString());

        String jsonResponse = response.jsonPath().getString("json.name");
        Assert.assertEquals(jsonResponse, "John", "Имя в ответе должно быть 'John'");
    }

    @Test
    public void postmanEchoDeleteStatus200(){
        Response response = RestAssured.given()
                .contentType("application/json")
                .when()
                .delete("/delete");

        Assert.assertEquals(response.getStatusCode(), 200, "Статус ответа должен быть 200");
    }
}
