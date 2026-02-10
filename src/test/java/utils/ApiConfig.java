package utils;

public final class ApiConfig {
    private ApiConfig() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static final String BASE_URI = "http://localhost:8686";
    public static final String LOGIN_ENDPOINT = "api/login";
    public static final String ADMIN_EMAIL = "admin@example.com";
    public static final String ADMIN_PASSWORD = "admin123";
}

