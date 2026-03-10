package pages;

import base.BasePage.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductDashboardPage extends BasePage {
    private final By pageTitle = By.xpath("//h2[text()='Products List ']");


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

    private By getProductByNameLocator(String productName) {
        return By.xpath(String.format("//tr[td[1][normalize-space(text())='%s']]", productName));
    }
    public boolean isProductDisplayed(String productName) {
        try {
            return waitForVisibility(getProductByNameLocator(productName)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
