package library.demo.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "users")
public class User {
    @Id
    private String id;
    @Indexed(unique = true)
    @NotNull(message = "Pole nie moze byc puste")
    @Email
    private String email;
    @Pattern(regexp = "^[a-zA-Z].*\\W.*$", message = "Pole musi zawierac jedna duzo litere i jeden znak specjalny")
    @NotNull(message = "Pole nie moze byc puste")
    @Size(min = 5, max = 20)
    private String password;

    public User(ObjectId id, String name, String password) {
        this.id = id.toHexString();
        this.email = name;
        this.password = password;
    }
}
