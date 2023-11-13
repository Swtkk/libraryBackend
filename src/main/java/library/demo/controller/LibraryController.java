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

        return libraryService.addBook(book.getKind(),book.getTitle(),book.getAuthor(),book.getCover(),book.getEpoch(),book.isHasAudio(),book.getGenre(),book.getSimpleThumb());

    }
//    @PostMapping()
//    public void addBook(@RequestBody Book book) throws AlreadyExistException {
//        libraryService.addBook(book);
//    }

}
