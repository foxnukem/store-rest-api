package com.store.store.repository;

import com.store.store.model.cart.CartProductQuantity;
import com.store.store.model.cart.CartProductQuantityId;
import org.springframework.data.repository.ListCrudRepository;

public interface CartProductQuantityRepository extends ListCrudRepository<CartProductQuantity, CartProductQuantityId> {
}
