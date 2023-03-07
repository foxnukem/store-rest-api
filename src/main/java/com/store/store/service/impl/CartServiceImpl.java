package com.store.store.service.impl;

import com.store.store.exception.NullEntityReferenceException;
import com.store.store.exception.UnacceptableParameterValueException;
import com.store.store.model.cart.Cart;
import com.store.store.model.cart.CartProductQuantity;
import com.store.store.model.cart.CartProductQuantityId;
import com.store.store.model.cart.OrderStatus;
import com.store.store.model.product.Product;
import com.store.store.repository.CartProductQuantityRepository;
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
    private final CartProductQuantityRepository cartProductQuantityRepository;

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
    public List<Cart> findAllCartsByUser(long userId) {
        return cartRepository.findAllByUserId(userId);
    }

    @Override
    public List<Cart> findAllByOrderStatus(String status) {
        if (status == null || status.isEmpty()) {
            throw new UnacceptableParameterValueException("Category name is null or empty");
        }
        OrderStatus orderStatus = OrderStatus.valueOf(status.toUpperCase());
        return cartRepository.findAllByStatus(orderStatus);
    }

    @Override
    public Cart changeOrderStatus(long cartId, OrderStatus status) {
        Cart cart = findById(cartId);
        switch (cart.getStatus()) {
            case DRAFT -> changeStatusToPAYED(status, cart);
            case PAYED -> changeStatusToDELIVERINGorREFUNDED(status, cart);
            case DELIVERING -> changeStatusToCOMPLETEDorRETURNING(status, cart);
            case RETURNING -> changeStatusToREFUNDED(status, cart);
        }
        return save(cart);
    }

    private void changeStatusToREFUNDED(OrderStatus status, Cart cart) {
        if (status == OrderStatus.REFUNDED) {
            cart.setStatus(OrderStatus.REFUNDED);
        }
    }

    private void changeStatusToCOMPLETEDorRETURNING(OrderStatus status, Cart cart) {
        if (status == OrderStatus.COMPLETED) {
            cart.setStatus(OrderStatus.COMPLETED);
        } else if (status == OrderStatus.RETURNING) {
            cart.setStatus(OrderStatus.RETURNING);
        }
    }

    private void changeStatusToDELIVERINGorREFUNDED(OrderStatus status, Cart cart) {
        if (status == OrderStatus.REFUNDED) {
            cart.setStatus(OrderStatus.REFUNDED);
        } else if (status == OrderStatus.DELIVERING) {
            cart.setStatus(OrderStatus.DELIVERING);
        }
    }

    private void changeStatusToPAYED(OrderStatus status, Cart cart) {
        if (status == OrderStatus.PAYED) {
            cart.setStatus(OrderStatus.PAYED);
        }
    }

    @Override
    public void setQuantityOfProductsInCart(long cartId, long productId, long quantity) {
        Cart cart = findById(cartId);
        Product product = productService.findById(productId);

        CartProductQuantityId id = new CartProductQuantityId();
        id.setCartId(cartId);
        id.setProductId(productId);

        if (quantity == 0L) {
            cartProductQuantityRepository.deleteById(id);
        }

        CartProductQuantity cartProductQuantity = new CartProductQuantity();
        cartProductQuantity.setCart(cart);
        cartProductQuantity.setProduct(product);
        cartProductQuantity.setQuantity(quantity);

        cartProductQuantityRepository.save(cartProductQuantity);
    }
}
