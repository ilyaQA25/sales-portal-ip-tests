package utils.productApi;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utils.ApiConfig;
import utils.TokenGenerator;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ProductsApiClient {
    public static void deleteProductByName(String productName) {
        String cleanToken = TokenGenerator.getAccessToken();
        String tokenForApi = "Bearer " + cleanToken;
        Response productsResponse = given()
                .baseUri(ApiConfig.BASE_URI)
                .header("Authorization", tokenForApi)
                .contentType(ContentType.JSON)
                .when()
                .get("/api/products")
                .then()
                .statusCode(200)
                .extract()
                .response();
        List<ProductDto> products = productsResponse.jsonPath().getList("products", ProductDto.class);
        String productId = null;
        for (ProductDto product : products) {
            if (product.getName() != null && product.getName().equals(productName)) {
                productId = product.getId();
                break;
            }
        }
        if (productId != null && !productId.isEmpty()) {
            given()
                    .baseUri(ApiConfig.BASE_URI)
                    .header("Authorization", tokenForApi)
                    .contentType(ContentType.JSON)
                    .pathParam("productId", productId)
                    .when()
                    .delete("/api/products/{productId}")
                    .then()
                    .statusCode(200);
            System.out.println("Product deleted via API: " + productName + " (ID: " + productId + ")");
        } else {
            System.out.println("Product not found for deletion, no action required: " + productName);
        }
    }
}
