package library.demo.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;

@Data
@Document(collection = "reviews")
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    @Id
    private String id;
    @NotNull
    @Size(min = 5, max = 200, message = "Pole moze zawierac od 5 do 200 znakow")
    private String body;
    private LocalDate date;
    private String userName;


    public Review(ObjectId id,String body, LocalDate date, String userName) {
        this.id = id.toHexString();
        this.body = body;
        this.date = LocalDate.now();
        this.userName = userName;
    }


}















