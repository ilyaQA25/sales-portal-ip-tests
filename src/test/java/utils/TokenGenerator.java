package utils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tests.apiTest.LoginRequestDto;
import tests.apiTest.LoginResponseDto;

import static io.restassured.RestAssured.given;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenGenerator {
    private String token;
    private String userData;

    public static AuthData getAuthData() { // <--- Меняем возвращаемый тип метода
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

        String token = apiResponse.getHeader("Authorization");
        String userName = apiResponse.getHeader("X-User-Name");

        if (token == null) {
            throw new RuntimeException("Authorization header not found in API login response.");
        }
        if (!token.startsWith("Bearer ")) {
            token = "Bearer " + token; // Убеждаемся, что токен с префиксом
        }

        return new AuthData(token, userName); // <--- Возвращаем новый объект AuthData
    }
    }


