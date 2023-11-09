package library.demo.service;


import library.demo.controller.Exceptions.AlreadyExistException;
import library.demo.controller.Exceptions.NotFoundException;
import library.demo.model.Book;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibraryService {



    @Autowired
    private LibraryRepository libraryRepository;
    @Autowired
    private MongoTemplate mongoTemplate;


    public List<Book> getAllBooks() {
        return libraryRepository.findAll();
    }


<<<<<<< HEAD
    public Book getBookById(ObjectId bookId) {
=======
    public Book getBookById(String bookId) {

>>>>>>> third-branch
        Query query = new Query(Criteria.where("_id").is(bookId));
        Book book = mongoTemplate.findOne(query, Book.class);
        if(book==null){
            throw new NotFoundException("not found "+bookId);
        }
    return book;
    }

<<<<<<< HEAD
    public Book addBook(String kind, String title, String author,String cover,String epoch,boolean hasAudio, String genre) throws AlreadyExistException {
=======
    public Book addBook(String kind, String title, String author,String cover,String epoch,boolean hasAudio, String genre,String simpleThumb) throws AlreadyExistException {
>>>>>>> third-branch
        if(libraryRepository.findByTitle(title) != null){
            throw new AlreadyExistException("Book with that title already exist");
        }
        ObjectId objectId = new ObjectId();
<<<<<<< HEAD
        Book book = new Book();
            book.setId(objectId);
=======
        String stringId = objectId.toHexString();
        Book book = new Book();
            book.setId(stringId);
>>>>>>> third-branch
            book.setKind(kind);
            book.setTitle(title);
            book.setAuthor(author);
            book.setCover(cover);
            book.setEpoch(epoch);
            book.setHasAudio(hasAudio);
            book.setGenre(genre);
<<<<<<< HEAD
=======
            book.setSimpleThumb(simpleThumb);
>>>>>>> third-branch
        Book savedBook = libraryRepository.save(book);

        return savedBook;
    }


    public void deleteBookById(ObjectId bookId) {
        Book bookToDelete = libraryRepository.findById(bookId).orElseThrow(()-> new NotFoundException("Book with id "+bookId + " not found"));
        libraryRepository.delete(bookToDelete);
    }
//
    public void updateBook(ObjectId bookId,Book book) {
        Optional<Book> existingBook= libraryRepository.findById(bookId);

        if(existingBook.isPresent()){
            Book bookToUpdate = existingBook.get();
            bookToUpdate.setKind(book.getKind());
            bookToUpdate.setTitle(book.getTitle());
            bookToUpdate.setAuthor(book.getAuthor());
            bookToUpdate.setCover(book.getCover());
            bookToUpdate.setEpoch(book.getEpoch());
            bookToUpdate.setHasAudio(book.isHasAudio());
            bookToUpdate.setGenre(book.getGenre());
<<<<<<< HEAD

=======
            bookToUpdate.setSimpleThumb(book.getSimpleThumb());
>>>>>>> third-branch
            libraryRepository.save(bookToUpdate);
        }
    }


}





















