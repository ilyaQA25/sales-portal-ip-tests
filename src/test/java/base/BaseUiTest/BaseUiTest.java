package base.BaseUiTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.time.Duration;

public class BaseUiTest {
    protected WebDriver driver;
    protected WebDriverWait wait;

    @BeforeMethod
    public void open(){
        driver = new ChromeDriver();
        driver.get("http://localhost:8585/#/login");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    protected void clickElement(By locator){
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    protected void typeIntoElement(By locator, String text){
        wait.until(ExpectedConditions.elementToBeClickable(locator)).sendKeys(text);
    }

    protected WebElement waitForVisibility(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    @AfterMethod
    public void close(){
        if (driver != null) {
            driver.quit();
        }
    }
}
