package library.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Date;

@Data
@Document(collection = "reviews")
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    @Id
    private ObjectId id;

    private String body;
    private LocalDate date;
    private String userEmail;
    private String bookId;

    public Review(String body, LocalDate date, String userEmail, String bookId) {
        this.body = body;
        this.date = LocalDate.now();
        this.userEmail = userEmail;
        this.bookId = bookId;
    }


}















