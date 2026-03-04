package tests;

import base.BaseUiTest.BaseUiTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.LoginPage;
import utils.LoginDataProvider;
import utils.UiConfig;

import java.time.Duration;

public class LoginTestUI extends BaseUiTest {
    private LoginPage loginPage;

    @BeforeMethod
    public void initPageObject() {
        // driver is already created in BaseUiTest.open() because the parent's @BeforeMethod runs earlier
        loginPage = new LoginPage(driver);
    }

    @Test(description = "smoke test", priority = 1)
    public void successLogin() {
        loginPage.login(UiConfig.VALID_EMAIL, UiConfig.VALID_PASSWORD);
        Assert.assertTrue(loginPage.isDashboardDisplayed());
    }

    @Test(dataProvider = "negativeLoginDataUi",
            dataProviderClass = LoginDataProvider.class,
            priority = 2)
    public void negativeTests(String email, String password, String errorMessage) {
        loginPage.login(email, password);
        String actualError = loginPage.getErrorText();
        Assert.assertTrue(actualError.contains(errorMessage));
    }
}
