package library.demo.service;


import library.demo.controller.Exceptions.AlreadyExistException;
import library.demo.controller.Exceptions.NotFoundException;
import library.demo.model.Book;
import library.demo.model.Review;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
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



    public Book getBookById(String bookId) {

        Query query = new Query(Criteria.where("_id").is(bookId));
        Book book = mongoTemplate.findOne(query, Book.class);
        if(book==null){
            throw new NotFoundException("not found "+bookId);
        }
    return book;
    }

    public List<Review> getReviewsForBook(String bookId){
        Optional<Book> book = libraryRepository.findById(new ObjectId(bookId));

        if(book.isPresent()){
            return book.get().getReviews();
        }else {
            throw new NotFoundException("not found "+bookId);
        }
    }

    public Book addBook(String kind, String title, String author,String cover,String epoch,boolean hasAudio, String genre,String simpleThumb) throws AlreadyExistException {
        if(libraryRepository.findByTitle(title) != null){
            throw new AlreadyExistException("Book with that title already exist");
        }
        ObjectId objectId = new ObjectId();


        String stringId = objectId.toHexString();
        Book book = new Book();
            book.setId(stringId);
            book.setKind(kind);
            book.setTitle(title);
            book.setAuthor(author);
            book.setCover(cover);
            book.setEpoch(epoch);
            book.setHasAudio(hasAudio);
            book.setGenre(genre);
            book.setSimpleThumb(simpleThumb);

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

            bookToUpdate.setSimpleThumb(book.getSimpleThumb());
            libraryRepository.save(bookToUpdate);
        }
    }


    public List<Book> searchBooksByTitle(String title) {
        Query query = new Query(Criteria.where("title").regex(title, "i"));
        return mongoTemplate.find(query, Book.class);
    }
    public List<Book> searchByKind(String kind){
        Query query = new Query(Criteria.where("").regex(kind,"i"));
        return mongoTemplate.find(query, Book.class);
    }
}





















