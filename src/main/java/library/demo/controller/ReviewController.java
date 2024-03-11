package library.demo.controller;


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
@RequestMapping("/public")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @Autowired
    private LibraryService libraryService;

    @GetMapping("/reviews/{bookId}")
    public ResponseEntity<List<Review>> getReviewsForBook(@PathVariable String bookId) {
        List<Review> reviews = libraryService.getReviewsForBook(bookId);
        return ResponseEntity.ok(reviews);

    }

    @PostMapping("/user/{bookId}")
    public ResponseEntity<Review> addReviewToBook(@PathVariable String bookId, @RequestBody Review review) {
        ObjectId id = new ObjectId(bookId);
        Review createdReview = reviewService.addReviewToBook(review, id);
        return new ResponseEntity<>(createdReview, HttpStatus.CREATED);
    }

}
