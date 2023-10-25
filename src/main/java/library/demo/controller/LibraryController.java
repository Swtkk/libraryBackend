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

    @GetMapping()
    public List<Book> getAllBooks() {
        return libraryService.getAllBooks();
    }

//    @RequestMapping(value = "/{bookId}", method = RequestMethod.GET)
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
        Book createBook = libraryService.addBook(book.getDescription(),book.getTitle(),book.getAuthor(),book.getScore());
        return createBook;
    }
//    @PostMapping()
//    public void addBook(@RequestBody Book book) throws AlreadyExistException {
//        libraryService.addBook(book);
//    }

}
