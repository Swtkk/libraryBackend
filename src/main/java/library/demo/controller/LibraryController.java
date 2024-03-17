package library.demo.controller;


import library.demo.controller.Exceptions.AlreadyExistException;
import library.demo.model.Book;
import library.demo.service.LibraryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LibraryController {

    @Autowired
    private LibraryService libraryService;

    @GetMapping("/public/books")
    public ResponseEntity<List<Book>> getAllBooks() {
        return new ResponseEntity<List<Book>>(libraryService.getAllBooks(), HttpStatus.OK);
    }
    @GetMapping("/public/books/{bookId}")
    public Book getBookById(@PathVariable String bookId) {

        return libraryService.getBookById(bookId);
    }

    @DeleteMapping("/admin/books/{bookId}")
    public void deleteBookById(@PathVariable ObjectId bookId) {
        libraryService.deleteBookById(bookId);
    }

    @PutMapping("/admin/books/{bookId}")
    public void updateBook(@PathVariable ObjectId bookId, @RequestBody Book book) {
        libraryService.updateBook(bookId, book);
    }

    @PostMapping("/admin/add-book")
    public Book createBook(@RequestBody Book book) throws AlreadyExistException{
        return libraryService.addBook(book.getKind(),book.getTitle(),book.getAuthor(),book.getCover(),book.getEpoch(),book.isHasAudio(),book.getGenre(),book.getSimpleThumb());
    }



    @PostMapping("/user/{userId}/{bookId}")
    public void addFavoriteBook(@PathVariable String userId, @PathVariable String bookId){
        ObjectId objectUserId = new ObjectId(userId);
        Book book = libraryService.getBookById(bookId);
        libraryService.addBookToUser(objectUserId,book);
    }
    @DeleteMapping("/user/{userId}/{bookId}")
    public void removeFavoriteBook(@PathVariable String userId, @PathVariable String bookId){
        ObjectId objectUserId = new ObjectId(userId);
        Book book = libraryService.getBookById(bookId);
        libraryService.removeBookFromUser(objectUserId, book);
    }

    @GetMapping("/public/books/kind")
    public ResponseEntity<List<Book>> searchByKind(@RequestParam String kind) {
        List<Book> books = libraryService.searchByKind(kind);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }
    //wyszukiwanie po tytule
    @GetMapping("/public/books/search")
    public ResponseEntity<List<Book>> searchByTitle(@RequestParam String title) {
        List<Book> books = libraryService.searchBooksByTitle(title);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }
}
