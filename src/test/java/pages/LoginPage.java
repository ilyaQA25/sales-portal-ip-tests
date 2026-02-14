package pages;

import base.BasePage.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;



public class LoginPage extends BasePage {
    private final By emailField = By.id("emailinput");
    private final By psw = By.id("passwordinput");
    private final By loginButton = By.cssSelector(".btn.btn-primary");
    private final By homeIndicatorLocator = By.xpath("//h1[text()='Welcome to Sales Management Portal']");
    private final By errorMessage = By.xpath("//div[@class='toast-body']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void login(String email, String password) {
        typeIntoElement(emailField, email);
        typeIntoElement(psw, password);
        clickElement(loginButton);
    }

    public boolean isDashboardDisplayed() {
        try {
            return waitForVisibility(homeIndicatorLocator).isDisplayed();
        } catch (Exception e) {
            return false;

        }
    }

    public String getErrorText(){
       return waitForVisibility(errorMessage).getText();
    }

}



