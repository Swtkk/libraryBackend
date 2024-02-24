package library.demo.controller;


import library.demo.model.Review;
import library.demo.service.ReviewService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;
    @PostMapping("/{bookId}")
    public ResponseEntity<Review> addReviewToBook(@PathVariable String bookId, @RequestBody Review review) {
        ObjectId id = new ObjectId(bookId);
        Review createdReview = reviewService.addReviewToBook(review, id);
        return new ResponseEntity<>(createdReview, HttpStatus.CREATED);
    }

}
