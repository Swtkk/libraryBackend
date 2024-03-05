package library.demo.controller;


import jakarta.validation.Valid;
import library.demo.controller.Exceptions.AlreadyExistException;
import library.demo.controller.Exceptions.ValidationException;
import library.demo.model.Book;
import library.demo.service.LibraryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class LibraryController {

    @Autowired
    private LibraryService libraryService;

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
    public void updateBook(@PathVariable ObjectId bookId, @RequestBody Book book) {
        libraryService.updateBook(bookId, book);
    }

    @PostMapping()
    public Book createBook(@Valid @RequestBody Book book, BindingResult result) throws AlreadyExistException {

        if (result.hasErrors()) {
            throw new ValidationException(result);
        }

        return libraryService.addBook(book.getKind(), book.getTitle(), book.getAuthor(), book.getCover(), book.getEpoch(), book.isHasAudio(), book.getGenre(), book.getSimpleThumb());

    }


    //wyszukiwanie po tytule
    @GetMapping("/search")
    public ResponseEntity<List<Book>> searchByTitle(@RequestParam String title) {
        List<Book> books = libraryService.searchBooksByTitle(title);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/kind")
    public ResponseEntity<List<Book>> searchByKind(@RequestParam String kind) {
        List<Book> books = libraryService.searchByKind(kind);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }
}
