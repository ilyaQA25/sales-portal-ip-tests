package tests;

import base.BaseUiTest.BaseUiTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.ProductAddPage;
import pages.ProductAddPage;
import utils.Manufacturer;
import utils.UiConfig;

public class CreateProductTest extends BaseUiTest {
    ProductAddPage productAddPage;

    @BeforeMethod
    public void initProductPage(){
        productAddPage = new ProductAddPage(driver);
    }

    @Test
    public void addProduct(){
        openPageWithAuth(UiConfig.PRODUCT_ADD_URL);
        productAddPage.createProduct("car","2", "7", Manufacturer.SAMSUNG);
    }
}
