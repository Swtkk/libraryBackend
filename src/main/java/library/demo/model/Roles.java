package library.demo.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "roles")
public class Roles {

    @Id
    private String id;
    private String name;

    public Roles(String role){
        this.name = role;
    }

}
