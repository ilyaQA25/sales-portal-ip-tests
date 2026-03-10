package tests;

import base.BaseUiTest.BaseUiTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.ProductAddPage;
import pages.ProductAddPage;
import pages.ProductDashboardPage;
import utils.Manufacturer;
import utils.UiConfig;

public class CreateProductTest extends BaseUiTest {
    ProductAddPage productAddPage;
    ProductDashboardPage productDashboardPage;

    @BeforeMethod
    public void initProductPage(){
        productAddPage = new ProductAddPage(driver);
        productDashboardPage = new ProductDashboardPage(driver);
    }

    @Test(description = "smoke test", priority = 1)
    public void addProduct(){
        openPageWithAuth(UiConfig.PRODUCT_ADD_URL);
        productAddPage.createProduct("car9","2", "453", Manufacturer.SAMSUNG);
        Assert.assertTrue(productDashboardPage.isProductDashboardDisplayed(),
                "After adding product, should be redirected to Products List Page.");
        Assert.assertTrue(productDashboardPage.isProductDisplayed("car3"));
    }

    @Test(description = "negative test", priority = 2)
    public void errorAmountMess(){
        openPageWithAuth(UiConfig.PRODUCT_ADD_URL);
        productAddPage.fillProductForm("ps9","2","just letters",Manufacturer.APPLE);
        Assert.assertTrue(productAddPage.isErrorMessAppears());
    }
}
