package pages;

import base.BasePage.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DashboardPage extends BasePage {

    public DashboardPage(WebDriver driver){
        super(driver);
    }

    private final By viewOrdersButton = By.id("orders-from-home");
    private final By viewProductsButton = By.id("products-from-home");
    private final By viewCustomersButton = By.id("customers-from-home");
    private final By homeHeader = By.xpath("//h1[text()='Welcome to Sales Management Portal']");

    public boolean isOpened(){
        try {
            return waitForVisibility(viewCustomersButton).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    public boolean isOnDashboardPage() {
        return driver.getCurrentUrl().endsWith("/#/home");
    }

    public String getPageTitleText() {
        return getText(homeHeader);
    }

    
}
