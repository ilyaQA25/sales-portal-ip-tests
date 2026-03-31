package utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Manufacturer {
    APPLE("Apple"),
    SAMSUNG("Samsung"),
    GOOGLE("Google"),
    MICROSOFT("Microsoft"),
    SONY("Sony"),
    XIAOMI("Xiaomi"),
    AMAZON("Amazon"),
    TESLA("Tesla");

    private final String visibleText;
}