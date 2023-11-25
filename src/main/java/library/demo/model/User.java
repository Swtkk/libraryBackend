package library.demo.model;

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
    private String name;
    private String password;

    public User(ObjectId id,String name, String password) {
        this.id = id.toHexString();
        this.name = name;
        this.password = password;
    }
}
