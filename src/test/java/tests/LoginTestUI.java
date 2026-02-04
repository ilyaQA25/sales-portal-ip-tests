package tests;

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

public class LoginTestUI {
    private WebDriver driver;
    private final By emailField = By.id("emailinput");
    private final By psw = By.id("passwordinput");
    private final By loginButton = By.cssSelector(".btn.btn-primary");
    private final By homeIndicatorLocator = By.xpath("//h1[text()='Welcome to Sales Management Portal']");
    private final By errorMessage = By.xpath("//div[@class='toast-body']");

    @BeforeTest
    public void open(){
        driver = new ChromeDriver();
        driver.get("http://localhost:8585/#/login");
    }

    @Test(description = "smoke test")
    public void successLogin(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.findElement(emailField).sendKeys("admin@example.com");
        driver.findElement(psw).sendKeys("admin123");
        driver.findElement(loginButton).click();
        WebElement homeIndicator = wait.until(ExpectedConditions.visibilityOfElementLocated(homeIndicatorLocator));
        Assert.assertTrue(homeIndicator.isDisplayed());
    }

    @Test(description = "negative test")
    public void wrongEmail(){
        driver.findElement(emailField).sendKeys("abracadabra@gmail");
        driver.findElement(psw).sendKeys("admin123");
        driver.findElement(loginButton).click();
        Assert.assertTrue(driver.findElement(errorMessage).isDisplayed());
    }

    @AfterTest
    public void close(){
        driver.quit();
    }
}