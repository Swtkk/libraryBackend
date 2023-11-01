package library.demo.service;


import jakarta.annotation.PostConstruct;
import library.demo.controller.Exceptions.AlreadyExistException;
import library.demo.controller.Exceptions.NotFoundException;
import library.demo.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LibraryDataBase {

    private List<Book> libraryDataBase;

    private LibraryRepository libraryRepository;

    private int IdCount =0;
//    @PostConstruct
//    public void loadData() {
//        libraryDataBase = new ArrayList<>();
//        libraryDataBase.add(new Book(++IdCount, "Little description", "Title of Book", "Author"));
//        libraryDataBase.add(new Book(++IdCount, "A big short description", "Book of Authorization", "Very big fish"));
//    }

    public List<Book> getAllBooks() {
        return libraryRepository.findAll();
    }

    public Book getBookById(int bookId) {
        return libraryDataBase.stream()
                .filter(book -> book.getId().equals(bookId)) // zmiana na equals
                .findFirst()
                .orElseThrow(() -> new NotFoundException("not Found" + bookId));
    }

    public Book addBook(String kind, String title, String author,String cover, String genre) throws AlreadyExistException {
        Book book = new Book(++IdCount,kind,title,author);

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
        libraryDataBase.removeIf(book -> book.getId().equals(bookId) ); //zmiana na equals
    }

    public void updateBook(Book book) {
        //For Title
        libraryDataBase.stream()
                // Or title.getTitle() == book.getTitle()
                .filter(title -> title.getId() == book.getId())
                .forEach(title -> title.setTitle(book.getTitle()));

    }


}





















