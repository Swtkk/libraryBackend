package library.demo.service;

import library.demo.controller.Exceptions.NotFoundException;
import library.demo.model.Book;
import library.demo.model.Review;
import library.demo.model.UserEntity;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private LibraryService libraryService;
    @Autowired
    private LibraryRepository libraryRepository;

    public Review addReviewToBook(Review review, ObjectId id) {
        review.setDate(LocalDate.now());
        review = reviewRepository.insert(review);
        mongoTemplate.updateFirst(
                Query.query(Criteria.where("_id").is(id)),
                new Update().push("reviews", review),
                Book.class);

        return review;
    }
    public void removeReviewFromBook(ObjectId bookId, Review review){
        mongoTemplate.updateFirst(
                Query.query(Criteria.where("_id").is(bookId)),
                new Update().pull("reviews", review), Book.class
        );
        //do przetestowania
    }

    public Review getReviewById(String reviewId) {

        Query query = new Query(Criteria.where("_id").is(reviewId));
        Review review = mongoTemplate.findOne(query, Review.class);
        if(review==null){
            throw new NotFoundException("not found "+ reviewId);
        }
        return review;
    }
}