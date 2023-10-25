package library.demo.service;


import jakarta.annotation.PostConstruct;
import library.demo.controller.AlreadyExistException;
import library.demo.controller.NotFoundException;
import library.demo.model.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class LibraryDataBase {

    private List<Book> libraryDataBase;

    private int IdCount =0;
    @PostConstruct
    public void loadData() {
        libraryDataBase = new ArrayList<>();
        libraryDataBase.add(new Book(++IdCount, "Little description", "Title of Book", "Author", 4));
        libraryDataBase.add(new Book(++IdCount, "A big short description", "Book of Authorization", "Very big fish", 8));
    }

    public List<Book> getAllBooks() {
        return libraryDataBase;
    }

    public Book getBookById(int bookId) {
        return libraryDataBase.stream()
                .filter(book -> book.getId() == bookId)
                .findFirst()
                .orElseThrow(() -> new NotFoundException("not Found" + bookId));
    }

    public Book addBook(String desc, String title, String author,int score) throws AlreadyExistException {
        Book book = new Book(++IdCount,desc,title,author,score);

        Optional<Book> addBook = libraryDataBase.stream()
                .filter(book1 -> book1.getId() == book.getId())
                .findFirst();
        Optional<Book> addBookTitle = libraryDataBase.stream()
                .filter(book1 -> book1.getTitle().equals(title))
                .findFirst();
        if(addBook.isPresent()){
            throw new AlreadyExistException("Book with that id already exist");
        }
        if(addBookTitle.isPresent()){
            throw new AlreadyExistException("Book with that title already exist");
        }
        libraryDataBase.add(book);
        return book;
    }

//    public void addBook(Book book) throws AlreadyExistException {
//        Optional<Book> addBookById = libraryDataBase.stream()
//                .filter(book1 -> book1.getId() == book.getId())
//                .findFirst();
//        if(addBookById.isPresent()){
//            throw new AlreadyExistException("book with that id already exist");
//        }
//
//        libraryDataBase.add(book);
//    }


    public void deleteBookById(int bookId) {
        libraryDataBase.removeIf(book -> book.getId() == bookId);
    }

    public void updateBook(Book book) {
        //For Title
        libraryDataBase.stream()
                // Or title.getTitle() == book.getTitle()
                .filter(title -> title.getId() == book.getId())
                .forEach(title -> title.setTitle(book.getTitle()));

        //For description
        libraryDataBase.stream()
                // Or desc.getDescription() == book.getDescription()
                .filter(desc -> desc.getId() == book.getId())
                .forEach(desc -> desc.setDescription(book.getDescription()));
    }


}





















