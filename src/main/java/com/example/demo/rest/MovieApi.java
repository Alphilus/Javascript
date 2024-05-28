package com.example.demo.rest;

import com.example.demo.entity.Review;
import com.example.demo.model.request.UpsertReviewRequest;
import com.example.demo.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class MovieApi {
    private final ReviewService reviewService;

    // Tạo review
    @PostMapping
    public ResponseEntity<?> createReview(@RequestBody UpsertReviewRequest request) {
        try {
            Review review = reviewService.createReview(request);
            return new ResponseEntity<>(review, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Cập nhật review
    @PutMapping("/{id}")
    public ResponseEntity<?> updateReview(@RequestBody UpsertReviewRequest request, @PathVariable Integer id) {
        Review review = reviewService.updateReview(id, request);
        return ResponseEntity.ok(review); // 200
    }

    // Xóa review
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteReview(@PathVariable Integer id) {
        reviewService.deleteReview(id);
        return ResponseEntity.noContent().build(); // 204
    }

}
