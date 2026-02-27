package utils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tests.apiTest.LoginRequestDto;
import tests.apiTest.LoginResponseDto;

import static io.restassured.RestAssured.given;

public class TokenGenerator {


    public static String getAccessToken() {
        LoginRequestDto requestBody = new LoginRequestDto(ApiConfig.ADMIN_EMAIL, ApiConfig.ADMIN_PASSWORD);

        Response apiResponse = given()
                .baseUri(ApiConfig.BASE_URI)
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post(ApiConfig.LOGIN_ENDPOINT)
                .then()
                .statusCode(200)
                .extract()
                .response();

        String authorizationHeader = apiResponse.getHeader("Authorization");

        if (authorizationHeader != null) {
            if (authorizationHeader.startsWith("Bearer ")) {
                return authorizationHeader.substring(7);
            }
            return authorizationHeader;
        } else {
            throw new RuntimeException("Authorization header not found in API login response.");
        }
    }
    }


