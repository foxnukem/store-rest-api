package com.store.store.controller;

import com.store.store.dto.CartDTO;
import com.store.store.dto.ProductQuantityDTO;
import com.store.store.model.cart.OrderStatus;
import com.store.store.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/carts")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @PreAuthorize("hasAnyAuthority('MANAGER', 'ADMIN')")
    @GetMapping("/status/{orderStatus}")
    ResponseEntity<List<CartDTO>> getAll(@PathVariable String orderStatus) {
        var carts = cartService.findAllByOrderStatus(orderStatus).stream()
                .map(CartDTO::convertEntityToDTO)
                .toList();
        return ResponseEntity.ok(carts);
    }

    @GetMapping("/statuses")
    ResponseEntity<List<String>> getAllStatuses() {
        return ResponseEntity.ok(Stream.of(OrderStatus.values())
                .map(OrderStatus::name)
                .toList()
        );
    }

    @PreAuthorize("hasAnyAuthority('MANAGER', 'ADMIN') or @cartRepository.findAllByUserId(authentication.principal.id).contains(@cartRepository.findById(#cartId))")
    @GetMapping("/{cartId}")
    ResponseEntity<CartDTO> getById(@PathVariable("cartId") long cartId) {
        var cart = CartDTO.convertEntityToDTO(cartService.findById(cartId));
        return ResponseEntity.ok(cart);
    }

    @PreAuthorize("hasAnyAuthority('MANAGER', 'ADMIN') or #userId==authentication.principal.id")
    @GetMapping("/users/{userId}")
    ResponseEntity<List<CartDTO>> getByUserId(@PathVariable("userId") long userId) {
        var carts = cartService.findAllCartsByUser(userId).stream()
                .map(CartDTO::convertEntityToDTO)
                .toList();
        return ResponseEntity.ok(carts);
    }

    @PostMapping
    ResponseEntity<CartDTO> addCart(@RequestBody CartDTO cart) {
        var persistedCart = cartService.save(CartDTO.convertDTOToEntity(cart));
        return ResponseEntity.ok(CartDTO.convertEntityToDTO(persistedCart));
    }

    @PreAuthorize("@cartRepository.findAllByUserId(authentication.principal.id).contains(@cartRepository.findById(#cartId))")
    @RequestMapping(path = "/{cartId}/products", method = {RequestMethod.PUT, RequestMethod.PATCH})
    ResponseEntity<CartDTO> updateProduct(@PathVariable("cartId") long cartId, @RequestBody List<ProductQuantityDTO> productQuantityDTOS) {
        productQuantityDTOS.forEach(pq -> cartService.setQuantityOfProductsInCart(cartId, pq.productId(), pq.quantity()));
        return ResponseEntity.ok(CartDTO.convertEntityToDTO(cartService.findById(cartId)));
    }

    @PreAuthorize("hasAnyAuthority('MANAGER', 'ADMIN') or @cartRepository.findAllByUserId(authentication.principal.id).contains(@cartRepository.findById(#cartId))")
    @PutMapping("/{cartId}/change-status/{status}")
    ResponseEntity<CartDTO> changeOrderStatus(@PathVariable("cartId") long cartId, @PathVariable("status") String status) {
        return ResponseEntity.ok(CartDTO.convertEntityToDTO(cartService.changeOrderStatus(cartId, OrderStatus.valueOf(status.toUpperCase()))));
    }
}
