package com.store.store.dto;

import com.store.store.model.cart.Cart;
import com.store.store.model.cart.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;

public record CartDTO(long id, LocalDateTime createdAt, long userId, String status,
                      List<ProductQuantityDTO> productsQuantity) {
    public static CartDTO convertEntityToDTO(Cart entity) {
        return new CartDTO(
                entity.getId(),
                entity.getCreatedAt(),
                entity.getUser().getId(),
                entity.getStatus().name(),
                entity.getProducts().stream()
                        .map(ProductQuantityDTO::convertEntityToDTO)
                        .toList());
    }

    public static Cart convertDTOToEntity(CartDTO dto) {
        Cart cart = new Cart();
        cart.setId(dto.id);
        cart.setStatus(OrderStatus.valueOf(dto.status.toUpperCase()));
        if (cart.getCreatedAt() == null) {
            cart.setCreatedAt(LocalDateTime.now());
        }
        return cart;
    }
}
