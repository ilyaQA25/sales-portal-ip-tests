package pages;

import base.BasePage.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductDashboardPage extends BasePage {
    private final By pageTitle = By.xpath("//h2[text()='Products List ']");
    private static final String PRODUCT_ROW_XPATH_PATTERN = "//tr[td[1][normalize-space(text())='%s']]";

    public ProductDashboardPage(WebDriver driver) {
        super(driver);
    }

    public boolean isProductDashboardDisplayed(){
        try {
            return waitForVisibility(pageTitle).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    @Step("check is product missing")
    public boolean isProductMissing(String productName) {
        return driver.findElements(getProductByNameLocator(productName)).isEmpty();
    }
    @Step("find product by product name")
    private By getProductByNameLocator(String productName) {
        return By.xpath(String.format(PRODUCT_ROW_XPATH_PATTERN, productName));
    }

    public boolean isProductDisplayed(String productName) {
        try {
            return waitForVisibility(getProductByNameLocator(productName)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
