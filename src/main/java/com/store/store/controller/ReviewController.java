package com.store.store.controller;

import com.store.store.dto.ReviewDTO;
import com.store.store.model.product.Product;
import com.store.store.model.product.Review;
import com.store.store.model.user.User;
import com.store.store.service.ProductService;
import com.store.store.service.ReviewService;
import com.store.store.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;
    private final ProductService productService;
    private final UserService userService;

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

    @PreAuthorize("#review.userId() == authentication.principal.id")
    @PostMapping
    ResponseEntity<ReviewDTO> addNewReview(@RequestBody ReviewDTO review) {
        Product product = productService.findById(review.productId());
        User author = userService.findById(review.userId());
        Review newReview = ReviewDTO.convertDTOToEntity(review, author, product);
        return ResponseEntity.ok(ReviewDTO.convertEntityToDTO(reviewService.save(newReview)));
    }

    @PreAuthorize("hasAuthority('ADMIN') or @reviewRepository.findById(#reviewId).orElse(new com.store.store.model.product.Review()).getAuthor().getId() == authentication.principal.id")
    @DeleteMapping("/{reviewId}")
    ResponseEntity<ReviewDTO> removeReview(@PathVariable("reviewId") long reviewId) {
        reviewService.delete(reviewId);
        return ResponseEntity.noContent().build();
    }
}
