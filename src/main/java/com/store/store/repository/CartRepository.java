package com.store.store.repository;

import com.store.store.model.cart.Cart;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CartRepository extends CrudRepository<Cart, Long> {
    List<Cart> findAllByUserId(Long id);
}
