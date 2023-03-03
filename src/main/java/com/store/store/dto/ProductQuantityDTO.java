package com.store.store.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.store.store.model.cart.CartProductQuantity;

public record ProductQuantityDTO(@JsonIgnore long cartId, long productId, long quantity) {
    public static ProductQuantityDTO convertEntityToDTO(CartProductQuantity entity) {
        return new ProductQuantityDTO(entity.getCart().getId(), entity.getProduct().getId(), entity.getQuantity());
    }

    //todo
    public static CartProductQuantity convertDTOToEntity(ProductQuantityDTO dto) {
        return null;
    }
}
