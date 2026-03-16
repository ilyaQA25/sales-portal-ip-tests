package utils;

import com.github.javafaker.Faker;

public class RandomUtils {
    private static final Faker faker = new Faker();

    public static String generateProductName() {
        return faker.commerce().productName();
    }

    public static String generateProductPrice() {
        return String.valueOf(faker.number().numberBetween(10, 1000));
    }

    public static String generateProductAmount() {
        return String.valueOf(faker.number().numberBetween(1, 100));
    }

    public static String generateRandomString() {
        return faker.lorem().word();
    }
}

