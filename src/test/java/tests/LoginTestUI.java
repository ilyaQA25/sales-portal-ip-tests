package tests;

import base.BaseUiTest.BaseUiTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginTestUI extends BaseUiTest {
    private final By emailField = By.id("emailinput");
    private final By psw = By.id("passwordinput");
    private final By loginButton = By.cssSelector(".btn.btn-primary");
    private final By homeIndicatorLocator = By.xpath("//h1[text()='Welcome to Sales Management Portal']");
    private final By errorMessage = By.xpath("//div[@class='toast-body']");


    @Test(description = "smoke test")
    public void successLogin(){
        typeIntoElement(emailField, "admin@example.com");
        typeIntoElement(psw,"admin123");
        clickElement(loginButton);
        WebElement homePageIndicator = waitForVisibility(homeIndicatorLocator);
        Assert.assertTrue(homePageIndicator.isDisplayed());
    }

    @Test(description = "negative test")
    public void wrongEmail(){
        typeIntoElement(emailField, "wersdfsdf");
        typeIntoElement(psw,"admin123");
        clickElement(loginButton);
        WebElement errorIndicator = waitForVisibility(errorMessage);
        Assert.assertTrue(errorIndicator.isDisplayed());
    }

}