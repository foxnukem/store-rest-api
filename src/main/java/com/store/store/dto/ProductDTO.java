package com.store.store.dto;

import com.store.store.model.product.Category;
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

    public static Product convertDTOToEntity(ProductDTO dto) {
        Product product = new Product();
        Category category1 = new Category();
        category1.setName(dto.category);
        product.setId(dto.id);
        product.setTitle(dto.title);
        product.setPrice(dto.price);
        product.setDescription(dto.description);
        product.setImageUrl(dto.imageUrl);
        product.setCategory(category1);
        return product;
    }
}

