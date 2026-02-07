package tests.apiTest;

import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class LoginTestAPI {

    @Test(description = "smoke test api")
    public void successfulLoginApiTest(){
        LoginRequestDto requestBody = new LoginRequestDto("admin@example.com","admin123");
        LoginResponseDto response = given()
                .baseUri("http://localhost:8686")
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("api/login")
                .then()
                .statusCode(200).extract().as(LoginResponseDto.class);

        Assert.assertEquals(response.getErrorMessage(),null);
    }

    @Test
    public void negativeLoginTestApi(){
        LoginRequestDto requestBody = new LoginRequestDto("admin@example.com","adm");
        LoginResponseDto response = given()
                .baseUri("http://localhost:8686")
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("api/login")
                .then()
                .statusCode(400).extract().as(LoginResponseDto.class);
        Assert.assertEquals(response.getErrorMessage(),"Incorrect credentials");
    }
}
