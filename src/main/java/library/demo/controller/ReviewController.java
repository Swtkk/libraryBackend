package library.demo.controller;


import jakarta.validation.Valid;
import library.demo.model.Book;
import library.demo.model.Review;
import library.demo.service.LibraryService;
import library.demo.service.ReviewService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @Autowired
    private LibraryService libraryService;

    @GetMapping("/public/user/{bookId}")
    public ResponseEntity<List<Review>> getReviewsForBook(@PathVariable String bookId) {
        List<Review> reviews = libraryService.getReviewsForBook(bookId);
        return ResponseEntity.ok(reviews);

    }
    @PostMapping("/user/review/{bookId}")
    public ResponseEntity<Review> addReviewToBook(@PathVariable String bookId,@Valid @RequestBody Review review) {
        ObjectId id = new ObjectId(bookId);
        Review createdReview = reviewService.addReviewToBook(review, id);
        return new ResponseEntity<>(createdReview, HttpStatus.CREATED);
    }
    @DeleteMapping("/user/review/{bookId}/{reviewId}")
    public void removeReviewFromBook(@PathVariable String bookId, @PathVariable String reviewId){
        ObjectId objectBookId = new ObjectId(bookId);
        Review review = reviewService.getReviewById(reviewId);
        reviewService.removeReviewFromBook(objectBookId, review);
    }


}
