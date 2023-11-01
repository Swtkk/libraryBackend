package library.demo.controller;


import library.demo.controller.Exceptions.AlreadyExistException;
import library.demo.model.Book;
import library.demo.service.LibraryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @RequestMapping(value = "/{bookId}", method = RequestMethod.GET)
    @GetMapping("/{bookId}")
    public Book getBookById(@PathVariable int bookId) {
        return libraryService.getBookById(bookId);
    }

//    @RequestMapping(value = "/{bookId}", method = RequestMethod.DELETE)
    @DeleteMapping("/{bookId}")
    public void deleteBookById(@PathVariable int bookId) {
        libraryService.deleteBookById(bookId);
    }

//    @RequestMapping(value = "/{bookId}", method = RequestMethod.PUT)
    @PutMapping("/{bookId}")
    public void updateDescription(@PathVariable int bookId, @RequestBody Book book){
        libraryService.updateBook(book);
    }

    @PostMapping()
   public Book createBook(@RequestBody Book book) throws AlreadyExistException {
        Book createBook = libraryService.addBook(book.getKind(),book.getTitle(),book.getAuthor(),book.getCover(),book.getGenre());
        return createBook;
    }
//    @PostMapping()
//    public void addBook(@RequestBody Book book) throws AlreadyExistException {
//        libraryService.addBook(book);
//    }

}
