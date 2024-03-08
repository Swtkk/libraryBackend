package library.demo.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "users")
public class UserEntity {
    @Id
    private String id;
    @NotNull(message = "Pole nie moze byc puste")
//    @Email
//    @Indexed(unique = true)
//    @Pattern(regexp = "^[a-zA-Z].*\\W.*$", message = "Pole musi zawierac jedna duzo litere i jeden znak specjalny")
    private String email;
    @NotNull(message = "Pole nie moze byc puste")
    @Size(min = 5, max = 20)
    private String password;
    private String roles;



}
