package base.BaseUiTest;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.TokenGenerator;
import utils.UiConfig;

public class BaseUiTest {
    protected WebDriver driver;

    @BeforeMethod
    public void open(){
        driver = new ChromeDriver();
        driver.get(UiConfig.BASE_URL);
    }

    protected void openPageWithAuth(String url, String token) {
        driver.get("http://localhost:8585/");
        driver.manage().deleteAllCookies();
        Cookie authCookie = new Cookie(
                "Authorization",
                token,
                "localhost",
                "/",
                null,
                false,
                false
        );
        driver.manage().addCookie(authCookie);
        driver.get(url);
        driver.navigate().refresh();
    }

    protected void openPageWithAuth(String url) {
        String validToken = TokenGenerator.getAccessToken();
        openPageWithAuth(url, validToken);
    }

    @AfterMethod
    public void close(){
        if (driver != null) {
            driver.quit();
        }
    }
}
