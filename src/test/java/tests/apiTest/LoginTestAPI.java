package tests.apiTest;

import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ApiConfig;

import static io.restassured.RestAssured.given;

public class LoginTestAPI {

    @Test(description = "smoke test api")
    public void successfulLoginApiTest(){
        LoginRequestDto requestBody = new LoginRequestDto(ApiConfig.ADMIN_EMAIL, ApiConfig.ADMIN_PASSWORD);
        LoginResponseDto response = given()
                .baseUri(ApiConfig.BASE_URI)
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post(ApiConfig.LOGIN_ENDPOINT)
                .then()
                .statusCode(200).extract().as(LoginResponseDto.class);

        Assert.assertEquals(response.getErrorMessage(),null);
    }

    @Test(description = "negative test api")
    public void negativeLoginTestApi(){
        LoginRequestDto requestBody = new LoginRequestDto(ApiConfig.ADMIN_EMAIL,"adm");
        LoginResponseDto response = given()
                .baseUri(ApiConfig.BASE_URI)
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post(ApiConfig.LOGIN_ENDPOINT)
                .then()
                .statusCode(400).extract().as(LoginResponseDto.class);
        Assert.assertEquals(response.getErrorMessage(),"Incorrect credentials");
    }
}
