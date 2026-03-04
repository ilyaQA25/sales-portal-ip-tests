package utils;

import org.testng.annotations.DataProvider;

public class LoginDataProvider {
    @DataProvider(name = "invalidTokens")
    public static Object[][] getInvalidTokens() { // Должен быть static!
        return new Object[][]{
                {UiConfig.INVALID_AUTH_TOKEN, "invalid signature"},
                {"", "empty token"}
        };
    }

    @DataProvider(name = "negativeLoginDataUi")
    public static Object[][] getNegativeData() {
        return new Object[][]{
                // { email, password, expectedError }
                {"wrong@email.com", UiConfig.VALID_PASSWORD, "Incorrect credentials"}, // Wrong email
                {UiConfig.VALID_EMAIL, "wrongPass", "Incorrect credentials"},      // wrong psw
                {"", "", "Incorrect credentials"}                                   // empty fields
        };
    }
}