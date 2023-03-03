package com.store.store.model.cart;

import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
public class CartProductQuantityId implements Serializable {
    private long cartId;
    private long productId;
}
