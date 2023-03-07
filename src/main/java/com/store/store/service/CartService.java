package com.store.store.service;

import com.store.store.exception.NullEntityReferenceException;
import com.store.store.exception.UnacceptableParameterValueException;
import com.store.store.model.cart.Cart;
import com.store.store.model.cart.OrderStatus;
import com.store.store.model.product.Product;
import com.store.store.model.user.User;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

public interface CartService {
    /**
     * Saves new or existing {@link Cart} entity.
     *
     * @param cart new or existing {@link Cart} entity
     * @return merged {@link Cart} entity
     * @throws NullEntityReferenceException if pass null
     */
    Cart save(Cart cart);

    /**
     * Finds {@link Cart} entity by id.
     *
     * @param id id of the {@link Cart}
     * @return {@link Cart} instance
     * @throws EntityNotFoundException if there is no record with such id
     */
    Cart findById(long id);

    /**
     * Returns list with {@link Cart} of the {@link User}
     *
     * @param userId id of the {@link User}
     * @return carts list of the user
     */
    List<Cart> findAllCartsByUser(long userId);

    /**
     * Returns list with {@link Cart} of the {@link User}
     *
     * @param status {@link OrderStatus} string representation
     * @return carts list of the user
     * @throws UnacceptableParameterValueException if pass null or an empty string
     */
    List<Cart> findAllByOrderStatus(String status);

    /**
     * Changes current status of the order.
     *
     * @param cartId id of the {@link Cart}
     * @param status new status value from {@link OrderStatus} enum
     * @return merged {@link Cart} entity
     */
    Cart changeOrderStatus(long cartId, OrderStatus status);

    /**
     * Sets quantity of products in cart.
     * If quantity == 0, removes such products from the cart completely.
     * If there was no such product in the cart and quantity > 0, adds that number in the cart
     *
     * @param cartId    id of the {@link Cart}
     * @param productId id of the {@link Product}
     * @param quantity  quantity of {@link Product}
     */
    void setQuantityOfProductsInCart(long cartId, long productId, long quantity);
}
