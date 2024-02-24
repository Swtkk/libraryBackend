package library.demo.service;

import library.demo.model.Book;
import library.demo.model.Review;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private MongoTemplate mongoTemplate;


    public Review addReviewToBook(Review review, ObjectId id) {//byc moze powinno byc ObjectId
        review.setDate(LocalDate.now());
        review = reviewRepository.insert(review);
        mongoTemplate.updateFirst(
                Query.query(Criteria.where("_id").is(id)),
                new Update().push("reviews", review),
                Book.class);

        return review;
    }

}
