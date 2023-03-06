package com.store.store.controller;

import com.store.store.dto.CategoryDTO;
import com.store.store.dto.ProductDTO;
import com.store.store.service.ProductService;
import com.store.store.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final ReviewService reviewService;

    @GetMapping
    ResponseEntity<List<ProductDTO>> getAllProducts() {
        var products = productService.findAll().stream()
                .map(ProductDTO::convertEntityToDTO)
                .toList();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{productId}")
    ResponseEntity<ProductDTO> getProductById(@PathVariable("productId") long productId) {
        var product = ProductDTO.convertEntityToDTO(productService.findById(productId));
        return ResponseEntity.ok(product);
    }

    @GetMapping("/categories")
    ResponseEntity<List<CategoryDTO>> getAllCategories() {
        var categories = productService.findAllCategories().stream()
                .map(CategoryDTO::convertEntityToDTO)
                .toList();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/category/{categoryName}")
    ResponseEntity<List<ProductDTO>> getAllProductsByCategory(@PathVariable("categoryName") String categoryName) {
        var productsByCategory = productService.findAllProductsInCategory(categoryName).stream()
                .map(ProductDTO::convertEntityToDTO)
                .toList();
        return ResponseEntity.ok(productsByCategory);
    }

    //todo
    @PostMapping
    ResponseEntity<ProductDTO> addNewProduct(@RequestBody ProductDTO product) {
        return null;
    }

    //todo
    @RequestMapping(path = "/{productId}", method = {RequestMethod.PUT, RequestMethod.PATCH})
    ResponseEntity<ProductDTO> updateExistingProduct(@PathVariable("productId") long productId) {
        return null;
    }

    @DeleteMapping("/{productId}")
    ResponseEntity<?> delete(@PathVariable("productId") long productId) {
        productService.delete(productId);
        return ResponseEntity.noContent().build();
    }
}
