package com.store.store.dto;

import com.store.store.model.cart.Cart;

import java.time.LocalDateTime;
import java.util.List;

public record CartDTO(long id, LocalDateTime createdAt, long userId, List<ProductQuantityDTO> productsQuantity) {
    public CartDTO convertEntityToDTO(Cart entity) {
        return new CartDTO(
                entity.getId(),
                entity.getCreatedAt(),
                entity.getUser().getId(),
                entity.getProducts().stream()
                        .map(ProductQuantityDTO::convertEntityToDTO)
                        .toList())
                ;
    }

    //todo
    public Cart convertDTOToEntity(CartDTO dto) {
        return null;
    }
}
