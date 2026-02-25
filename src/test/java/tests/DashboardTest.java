package tests;

import base.BaseUiTest.BaseUiTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;

public class DashboardTest extends BaseUiTest {
    @Test
    public void openDashboardDirectly() throws InterruptedException {
        openPageWithAuth("http://localhost:8585/#/home");
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.isOnDashboardPage();
        dashboardPage.viewOrders();
    }
}
