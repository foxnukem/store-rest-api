package com.store.store.service;

import com.store.store.exception.NullEntityReferenceException;
import com.store.store.exception.UnacceptableParameterValueException;
import com.store.store.model.cart.Cart;
import com.store.store.model.cart.OrderStatus;
import com.store.store.model.user.User;
import com.store.store.repository.CartRepository;
import com.store.store.service.impl.CartServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CartServiceTests {
    @Mock
    private CartRepository cartRepository;

    @InjectMocks
    private CartServiceImpl cartService;

    private Cart cart;
    private User user;

    @BeforeEach
    void setup() {
        user = ServiceTestsUtils.buildTestUser();
        cart = new Cart();
        cart.setId(1L);
        cart.setUser(null);
    }

    @Test
    void whenCartIsNull_ThenThrowNullEntityReferenceException() {
        assertThrows(NullEntityReferenceException.class, () -> cartService.save(null));
    }

    @Test
    void whenCartIsValidAndNewAndNewCategory_ThenSaveIt() {
        when(cartRepository.save(any(Cart.class))).thenReturn(cart);
        assertEquals(cart, cartService.save(cart));
        verify(cartRepository).save(any(Cart.class));
    }

    @Test
    void whenCartExists_ThenReturnIt() {
        when(cartRepository.findById(anyLong())).thenReturn(Optional.of(cart));
        assertEquals(cart, cartService.findById(1L));
    }

    @Test
    void whenCartNotExist_ThenThrowEntityNotFoundException() {
        when(cartRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> cartService.findById(1L));
    }

    @Test
    void whenPassingCorrectUserIdButNoSuchUser_ThenReturnAnEmptyList() {
        when(cartRepository.findAllByUserId(anyLong())).thenReturn(Collections.emptyList());
        assertTrue(cartService.findAllCartsByUser(user.getId()).isEmpty());
    }

    @Test
    void whenPassingCorrectUserId_ThenReturnListWithCarts() {
        when(cartRepository.findAllByUserId(anyLong())).thenReturn(List.of(new Cart(), new Cart(), new Cart()));
        assertEquals(3, cartService.findAllCartsByUser(user.getId()).size());
    }

    @Test
    void whenPassingCorrectStatusButNoCartsWithSuchStatus_ThenReturnAnEmptyList() {
        when(cartRepository.findAllByStatus(OrderStatus.RETURNING)).thenReturn(Collections.emptyList());
        assertTrue(cartService.findAllByOrderStatus(OrderStatus.RETURNING.name()).isEmpty());
    }

    @Test
    void whenPassingCorrectStatus_ThenReturnListWithCarts() {
        when(cartRepository.findAllByStatus(OrderStatus.DRAFT)).thenReturn(List.of(new Cart(), new Cart(), new Cart(), new Cart()));
        assertEquals(4, cartService.findAllByOrderStatus(OrderStatus.DRAFT.name()).size());
    }

    @Test
    void whenPassingNullOrEmptyStringAsStatus_ThenThrowUnacceptableParameterValueException() {
        assertThrows(UnacceptableParameterValueException.class, () -> cartService.findAllByOrderStatus(null));
    }
}
