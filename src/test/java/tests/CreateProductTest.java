package tests;

import base.BaseUiTest.BaseUiTest;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.ProductAddPage;
import pages.ProductDashboardPage;
import utils.Manufacturer;
import utils.productApi.ProductsApiClient;
import utils.RandomUtils;
import utils.UiConfig;

public class CreateProductTest extends BaseUiTest {
    private ProductAddPage productAddPage;
    private ProductDashboardPage productDashboardPage;
    private String createdProductName;

    @BeforeMethod
    public void initProductPage() {
        productAddPage = new ProductAddPage(driver);
        productDashboardPage = new ProductDashboardPage(driver);
        createdProductName = null;
    }

    @Test(description = "smoke test", priority = 1)
    public void addProduct() {
        createdProductName = RandomUtils.generateProductName();
        String productPrice = RandomUtils.generateProductPrice();
        String productAmount = RandomUtils.generateProductAmount();
        openPageWithAuth(UiConfig.PRODUCT_ADD_URL);
        productAddPage.createProduct(createdProductName, productPrice, productAmount, Manufacturer.SAMSUNG);
        Assert.assertTrue(productDashboardPage.isProductDashboardDisplayed(),
                "After adding product, should be redirected to Products List Page.");
        Assert.assertTrue(productDashboardPage.isProductDisplayed(createdProductName));
    }

    @Test(description = "negative test: invalid amount", priority = 2)
    public void invalidAmountProduct() {
        createdProductName = RandomUtils.generateProductName() + " Neg";
        String invalidAmount = RandomUtils.generateRandomString(); // "abc"
        openPageWithAuth(UiConfig.PRODUCT_ADD_URL);
        productAddPage.fillProductForm(createdProductName, "100", invalidAmount, Manufacturer.APPLE);
        Assert.assertTrue(productAddPage.isErrorMessAppears(), "Error message should be visible");
        driver.get(UiConfig.PRODUCT_URL);
        Assert.assertTrue(productDashboardPage.isProductMissing(createdProductName),
                "Product should NOT be created with invalid data");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (createdProductName != null) {
            ProductsApiClient.deleteProductByName(createdProductName);
        }
    }
}