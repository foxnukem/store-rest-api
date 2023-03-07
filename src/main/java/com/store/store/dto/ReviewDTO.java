package com.store.store.dto;

import com.store.store.model.product.Product;
import com.store.store.model.product.Review;
import com.store.store.model.user.User;
import jakarta.validation.constraints.NotNull;

public record ReviewDTO(long id, long userId, long productId, @NotNull String text, double rating) {
    public static ReviewDTO convertEntityToDTO(Review entity) {
        return new ReviewDTO(
                entity.getId(),
                entity.getAuthor().getId(),
                entity.getProduct().getId(),
                entity.getText(),
                entity.getRating());
    }

    public static Review convertDTOToEntity(ReviewDTO dto, User user, Product product) {
        Review review = new Review();
        review.setAuthor(user);
        review.setProduct(product);
        review.setText(dto.text);
        review.setRating(dto.rating);
        return review;
    }
}
