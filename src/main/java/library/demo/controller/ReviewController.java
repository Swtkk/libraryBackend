package library.demo.controller;


import library.demo.model.Review;
import library.demo.service.ReviewService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;
    @PostMapping
    public ResponseEntity<Review> createReview(@RequestBody Map<String, String> payload){ //byc moze powinno byc objectId
        String stringId = payload.get("_id");
        ObjectId objectId = new ObjectId(stringId);
        return new ResponseEntity<Review>(reviewService.createReview(String.valueOf(payload.get("reviewBody")),objectId), HttpStatus.CREATED);
    }
}
