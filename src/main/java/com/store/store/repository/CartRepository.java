package com.store.store.repository;

import com.store.store.model.cart.Cart;
import com.store.store.model.cart.OrderStatus;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface CartRepository extends ListCrudRepository<Cart, Long> {
    List<Cart> findAllByUserId(Long id);
    List<Cart> findAllByStatus(OrderStatus status);
}
