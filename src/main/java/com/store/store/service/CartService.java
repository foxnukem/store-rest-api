package com.store.store.service;

import com.store.store.exception.NullEntityReferenceException;
import com.store.store.model.cart.Cart;
import com.store.store.model.cart.OrderStatus;
import com.store.store.model.user.User;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

public interface CartService {
    /**
     * Saves new or existing {@link Cart} entity. Throws {@link NullEntityReferenceException} if pass null.
     *
     * @param cart
     * @return merged {@link Cart} entity
     */
    Cart save(Cart cart);

    /**
     * Finds {@link Cart} entity by id. If there is no record with such id, throws {@link EntityNotFoundException}.
     *
     * @param id
     * @return {@link Cart} instance
     */
    Cart findById(long id);

    /**
     * Returns list with {@link Cart} of the {@link User}
     *
     * @param userId
     * @return carts list of the user
     */
    List<Cart> findAllCartsByUser(long userId);

    /**
     * Changes current status of the order.
     *
     * @param cartId
     * @param status
     * @return merged {@link Cart} entity
     */
    Cart changeOrderStatus(long cartId, OrderStatus status);

    /**
     * Sets quantity of products in cart.
     * If quantity == 0, removes such products from the cart completely.
     * If there was no such product in the cart and quantity > 0, adds that number in the cart
     *
     * @param cartId
     * @param productId
     * @param quantity
     */
    void setQuantityOfProductsInCart(long cartId, long productId, long quantity);
}
