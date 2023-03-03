package com.store.store.dto;

import com.store.store.model.product.Product;

import java.math.BigDecimal;
import java.util.List;

public record ProductDTO(long id, String title, BigDecimal price, String category, String description, String imageUrl,
                         List<ReviewDTO> reviews) {
    public static ProductDTO convertEntityToDTO(Product entity) {
        List<ReviewDTO> reviews = entity.getReviews().stream().map(ReviewDTO::convertEntityToDTO).toList();
        return new ProductDTO(
                entity.getId(),
                entity.getTitle(),
                entity.getPrice(),
                CategoryDTO.convertEntityToDTO(entity.getCategory()).name(),
                entity.getDescription(),
                entity.getImageUrl(),
                reviews
        );
    }

    //todo
    public static Product convertDTOToEntity(ProductDTO dto) {
        return null;
    }
}

