package tests.apiTest;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDto {
    @JsonProperty("IsSuccess")
    private boolean isSuccess;

    @JsonProperty("ErrorMessage")
    private String errorMessage;

    @JsonProperty("User")
    private UserDto user;

    @JsonProperty("token") // Если токен есть в ответе
    private String token;
}
