package library.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReqRes {

    private int statusCode;
    private String error;
    private String token;
    private String message;
    private String refreshToken;
    private String expirationTime;
    @NotNull(message = "Pole nie moze byc puste")
    private String email;
    private String role;
    @NotNull(message = "Pole nie moze byc puste")
    private String password;
    private UserEntity userEntity;
}
