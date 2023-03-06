package com.store.store.service.impl;

import com.store.store.exception.NullEntityReferenceException;
import com.store.store.model.cart.Cart;
import com.store.store.model.cart.OrderStatus;
import com.store.store.repository.CartRepository;
import com.store.store.service.CartService;
import com.store.store.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private static final String NOT_FOUND_MESSAGE = "Cart (id=%d) was not found";
    private static final String NULL_ENTITY_MESSAGE = "Cart cannot be 'null'";
    private final CartRepository cartRepository;
    private final ProductService productService;

    @Override
    public Cart save(Cart cart) {
        if (cart == null) {
            log.error(NULL_ENTITY_MESSAGE);
            throw new NullEntityReferenceException(NULL_ENTITY_MESSAGE);
        }
        return cartRepository.save(cart);
    }

    @Override
    public Cart findById(long id) {
        return cartRepository.findById(id).orElseThrow(() -> {
            log.error(NOT_FOUND_MESSAGE.formatted(id));
            throw new EntityNotFoundException(NOT_FOUND_MESSAGE.formatted(id));
        });
    }

    @Override
    public List<Cart> findAll() {
        return cartRepository.findAll();
    }

    @Override
    public List<Cart> findAllCartsByUser(long userId) {
        return cartRepository.findAllByUserId(userId);
    }

    //todo
    @Override
    public Cart changeOrderStatus(long cartId, OrderStatus status) {
        return null;
    }

    //todo
    @Override
    public void setQuantityOfProductsInCart(long cartId, long productId, long quantity) {

    }
}
