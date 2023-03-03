package com.store.store.model.cart;

import com.store.store.model.product.Product;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "carts_products_quantity")
@Data
@NoArgsConstructor
public class CartProductQuantity {
    @EmbeddedId
    private CartProductQuantityId id = new CartProductQuantityId();

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("cartId")
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("productId")
    private Product product;

    @Column(nullable = false)
    private long quantity;
}
