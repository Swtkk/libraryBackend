package library.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    private int id;
    private String description,title,author;
    private int score;

    public Book(int i, Book book) {

    }
}
