package library.demo.controller;


import library.demo.model.Book;
import library.demo.service.LibraryDataBase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/library")
public class LibraryController {


    private final LibraryDataBase libraryService;

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

}
