package library.demo.model;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
<<<<<<< HEAD
=======
import org.springframework.data.mongodb.core.mapping.Field;
>>>>>>> third-branch

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "books")
public class Book {
//    private int id;
    @Id
<<<<<<< HEAD
    private ObjectId id;
=======
    private String id;

    private String name;
>>>>>>> third-branch
    private String kind;
    private String fullSortKey;
    @Indexed(unique = true)
    private String title;
    private String url;
    private String coverColor;
    private String author;
    private String cover;
    private String epoch;
    private String href;
    private boolean hasAudio;
    private String genre;
<<<<<<< HEAD
=======
    @Field("simple_thumb")
>>>>>>> third-branch
    private String simpleThumb;
    private String slug;
    private String coverThumb;

    @DocumentReference
    private List<Review> reviews;

<<<<<<< HEAD
    public Book(ObjectId id, String kind, String title, String author, String cover, String epoch, boolean hasAudio, String genre) {
        this.id = id;
=======
    public Book(ObjectId id, String kind, String title, String author, String cover, String epoch, boolean hasAudio, String genre,String simpleThumb) {
        this.id = id.toHexString();
>>>>>>> third-branch
        this.kind = kind;
//        this.fullSortKey = fullSortKey;
        this.title = title;
//        this.url = url;
//        this.coverColor = coverColor;
        this.author = author;
        this.cover = cover;
        this.epoch = epoch;
//        this.href = href;
        this.hasAudio = hasAudio;
        this.genre = genre;
<<<<<<< HEAD
//        this.simpleThumb = simpleThumb;
=======
        this.simpleThumb = simpleThumb;
>>>>>>> third-branch
//        this.slug = slug;
//        this.coverThumb = coverThumb;
    }
}
