package tests;

import base.BaseUiTest.BaseUiTest;
import org.openqa.selenium.Cookie;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;
import utils.LoginDataProvider;
import utils.UiConfig;

public class DashboardTest extends BaseUiTest {
    private DashboardPage dashboardPage;

    @BeforeMethod
    public void initPageObject() {
        dashboardPage = new DashboardPage(driver);
    }

    @Test(description = "smoke test", priority = 1)
    public void openDashboardDirectly() {
        openPageWithAuth(UiConfig.HOME_URL);
        Assert.assertEquals(dashboardPage.getHomeHeader(), UiConfig.EXPECTED_DASHBOARD_TITLE);
    }

    @Test(description = "Negative Test: Access denied with various invalid tokens",
            dataProvider = "invalidTokens",
            dataProviderClass = LoginDataProvider.class,
            priority = 2)
    public void accessDeniedWithVariousInvalidTokens(String tokenValue, String description) {
        openPageWithAuth(UiConfig.HOME_URL, tokenValue);
        DashboardPage dashboardPage = new DashboardPage(driver);
        Assert.assertFalse(dashboardPage.isDashboardPageDisplayed(), "Dashboard page should NOT be opened with invalid token: " + description);
        Assert.assertTrue(driver.getCurrentUrl().contains("/#/login"), "Should be redirected to login page for: " + description);
    }
}