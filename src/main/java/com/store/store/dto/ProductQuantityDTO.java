package com.store.store.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.store.store.model.cart.Cart;
import com.store.store.model.cart.CartProductQuantity;
import com.store.store.model.product.Product;

public record ProductQuantityDTO(@JsonIgnore long cartId, long productId, long quantity) {
    public static ProductQuantityDTO convertEntityToDTO(CartProductQuantity entity) {
        return new ProductQuantityDTO(entity.getCart().getId(), entity.getProduct().getId(), entity.getQuantity());
    }

    public static CartProductQuantity convertDTOToEntity(ProductQuantityDTO dto, Product product, Cart cart) {
        CartProductQuantity productQuantity = new CartProductQuantity();
        productQuantity.setCart(cart);
        productQuantity.setProduct(product);
        productQuantity.setQuantity(dto.quantity);
        return productQuantity;
    }
}
