package utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Генерирует геттеры, сеттеры, equals, hashCode, toString
@NoArgsConstructor // Пустой конструктор (для Lombok)
@AllArgsConstructor // Конструктор со всеми полями (для удобства создания)
public class AuthData {
    private String token;
    private String userName;
}