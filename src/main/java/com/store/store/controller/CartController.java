package com.store.store.controller;

import com.store.store.dto.CartDTO;
import com.store.store.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carts")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @GetMapping
    ResponseEntity<List<CartDTO>> getAll() {
        var carts = cartService.findAll().stream()
                .map(CartDTO::convertEntityToDTO)
                .toList();
        return ResponseEntity.ok(carts);
    }

    @GetMapping("/{cartId}")
    ResponseEntity<CartDTO> getById(@PathVariable("cartId") long cartId) {
        var cart = CartDTO.convertEntityToDTO(cartService.findById(cartId));
        return ResponseEntity.ok(cart);
    }

    @GetMapping("/users/{userId}")
    ResponseEntity<List<CartDTO>> getByUserId(@PathVariable("userId") long userId) {
        var carts = cartService.findAllCartsByUser(userId).stream()
                .map(CartDTO::convertEntityToDTO)
                .toList();
        return ResponseEntity.ok(carts);
    }

    //todo
    @PostMapping
    ResponseEntity<CartDTO> addCart(@RequestBody CartDTO cart) {
        return null;
    }

    //todo
    @RequestMapping(path = "/{cartId}", method = {RequestMethod.PUT, RequestMethod.PATCH})
    ResponseEntity<CartDTO> updateProduct(@PathVariable("cartId") long cartId, @RequestBody CartDTO cart) {
        return null;
    }

    //todo
    @PutMapping("/{cartId}/{status}")
    ResponseEntity<CartDTO> changeOrderStatus(@PathVariable("cartId") long cartId, @PathVariable("status") String status) {
        return null;
    }
}
