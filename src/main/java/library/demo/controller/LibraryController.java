package library.demo.controller;


import library.demo.controller.Exceptions.AlreadyExistException;
import library.demo.controller.Exceptions.NotFoundException;
import library.demo.model.Book;
import library.demo.service.LibraryService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class LibraryController {

    @Autowired
    private LibraryService libraryService;

<<<<<<< HEAD
    @GetMapping()
    public ResponseEntity<List<Book>> getAllBooks() {
        return new ResponseEntity<List<Book>>(libraryService.getAllBooks(), HttpStatus.OK);
    }

//    @RequestMapping(value = "/{bookId}", method = RequestMethod.GET)
    @GetMapping("/{bookId}")
    public Book getBookById(@PathVariable String bookId) {

        return libraryService.getBookById(bookId);
    }

//    @RequestMapping(value = "/{bookId}", method = RequestMethod.DELETE)
    @DeleteMapping("/{bookId}")
    public void deleteBookById(@PathVariable ObjectId bookId) {
        libraryService.deleteBookById(bookId);
    }

//    @RequestMapping(value = "/{bookId}", method = RequestMethod.PUT)
    @PutMapping("/{bookId}")
    public void updateBook(@PathVariable ObjectId bookId, @RequestBody Book book){
        libraryService.updateBook(bookId,book);
    }

    @PostMapping()
   public Book createBook( @RequestBody Book book) throws AlreadyExistException {
=======
    @GetMapping("/users/{username}/books")
    public List<Book> getAllBooks(@PathVariable String username) {
        return libraryService.getAllBooks();
    }

    //    @RequestMapping(value = "/{bookId}", method = RequestMethod.GET)
    @GetMapping("/users/{username}/books/{bookId}")
    public Book getBookById(@PathVariable String username, @PathVariable int bookId) {
        return libraryService.getBookById(bookId);
    }

    //    @RequestMapping(value = "/{bookId}", method = RequestMethod.DELETE)
    @DeleteMapping("/users/{username}/books/{bookId}")
    public void deleteBookById(@PathVariable String username, @PathVariable int bookId) {
        libraryService.deleteBookById(bookId);
    }

    //    @RequestMapping(value = "/{bookId}", method = RequestMethod.PUT)
    @PutMapping("/users/{username}/books/{bookId}")
    public void updateDescription(@PathVariable String username, @PathVariable int bookId, @RequestBody Book book) {
        libraryService.updateBook(book);
    }

    @PostMapping("/users/{username}/books")
    public Book createBook(@RequestBody Book book) throws AlreadyExistException {
        Book createBook = libraryService.addBook(book.getDescription(), book.getTitle(), book.getAuthor(), book.getScore());
        return createBook;
    }
//    @PostMapping()
//    public void addBook(@RequestBody Book book) throws AlreadyExistException {
//        libraryService.addBook(book);
//    }
>>>>>>> origin/main

        return libraryService.addBook(book.getKind(),book.getTitle(),book.getAuthor(),book.getCover(),book.getEpoch(),book.isHasAudio(),book.getGenre(),book.getSimpleThumb());

    }

    //wyszukiwanie po tytule
    @GetMapping("/search")
    public ResponseEntity<List<Book>> searchByTitle(@RequestParam String title){
        List<Book> books = libraryService.searchBooksByTitle(title);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }
    @GetMapping("/kind")
    public ResponseEntity<List<Book>> searchByKind(@RequestParam String kind){
        List<Book> books = libraryService.searchByKind(kind);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }
}
