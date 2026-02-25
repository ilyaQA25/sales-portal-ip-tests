package base.BaseUiTest;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import utils.AuthData;
import utils.TokenGenerator;
import utils.UiConfig;

import java.time.Duration;

public class BaseUiTest {
    protected WebDriver driver;


    @BeforeMethod
    public void open(){
        driver = new ChromeDriver();
        driver.get(UiConfig.BASE_URL);
    }

    protected void openPageWithAuth(String url) {
        driver.get("http://localhost:8585/"); // Просто base url, без #/login

        // *** НОВЫЙ ШАГ: ОЧИЩАЕМ ВСЕ КУКИ ПЕРЕД ИНЖЕКТОМ ***
        driver.manage().deleteAllCookies();

        // 2. Получаем токен через API
        AuthData authData = TokenGenerator.getAuthData(); // <--- Получаем AuthData

        // Добавляем куку Authorization
        Cookie authCookie = new Cookie(
                "Authorization",
                authData.getToken(), // <--- Используем get-метод из AuthData
                "localhost",
                "/",
                null,
                false,
                false
        );
        driver.manage().addCookie(authCookie);

        if (authData.getUserName() != null) {
            Cookie userNameCookie = new Cookie(
                    "X-User-Name",
                    authData.getUserName(), // <--- Используем get-метод из AuthData
                    "localhost",
                    "/",
                    null,
                    false,
                    false
            );
            driver.manage().addCookie(userNameCookie);
        }

        driver.get(url);
        driver.navigate().refresh();
    }

    @AfterMethod
    public void close(){
        if (driver != null) {
            driver.quit();
        }
    }
}
