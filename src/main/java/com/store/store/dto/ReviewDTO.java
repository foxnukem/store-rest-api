package com.store.store.dto;

import com.store.store.model.product.Review;

public record ReviewDTO(long id, long userId, long productId, String text, double rating) {
    public static ReviewDTO convertEntityToDTO(Review entity) {
        return new ReviewDTO(
                entity.getId(),
                entity.getAuthor().getId(),
                entity.getProduct().getId(),
                entity.getText(),
                entity.getRating());
    }

    //todo
    public static Review convertDTOToEntity(ReviewDTO dto) {
        return null;
    }
}
