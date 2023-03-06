package com.store.store.controller;

import com.store.store.dto.ReviewDTO;
import com.store.store.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @GetMapping("/product/{productId}")
    ResponseEntity<List<ReviewDTO>> getReviewsOfProduct(@PathVariable("productId") long productId) {
        var reviews = reviewService.findAllReviewsByProductId(productId).stream()
                .map(ReviewDTO::convertEntityToDTO)
                .toList();
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/{reviewId}")
    ResponseEntity<ReviewDTO> getReviewById(@PathVariable("reviewId") long reviewId) {
        var review = ReviewDTO.convertEntityToDTO(reviewService.findById(reviewId));
        return ResponseEntity.ok(review);
    }

    //todo
    @PostMapping("/{productId}/reviews")
    ResponseEntity<ReviewDTO> addNewReview(@PathVariable("productId") long productId, @RequestBody ReviewDTO review) {
        return null;
    }

    @DeleteMapping("/{productId}/reviews/{reviewId}")
    ResponseEntity<ReviewDTO> removeReview(@PathVariable("productId") long productId, @PathVariable("reviewId") long reviewId) {
        reviewService.removeReview(reviewId);
        return ResponseEntity.noContent().build();
    }
}
