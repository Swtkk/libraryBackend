package library.demo.model;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class Login {

    private String email;

    private String password;
}
