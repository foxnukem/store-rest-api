package com.store.store.controller;

import com.store.store.dto.CategoryDTO;
import com.store.store.dto.ProductDTO;
import com.store.store.model.product.Product;
import com.store.store.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

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

    @PreAuthorize("hasAnyAuthority('MANAGER', 'ADMIN')")
    @PostMapping
    ResponseEntity<ProductDTO> addNewProduct(@RequestBody ProductDTO product) {
        Product newProduct = ProductDTO.convertDTOToEntity(product);
        return ResponseEntity.ok(ProductDTO.convertEntityToDTO(productService.save(newProduct)));
    }

    @PreAuthorize("hasAnyAuthority('MANAGER', 'ADMIN')")
    @RequestMapping(path = "/{productId}", method = {RequestMethod.PUT, RequestMethod.PATCH})
    ResponseEntity<ProductDTO> updateExistingProduct(@PathVariable("productId") long productId, @RequestBody ProductDTO product) {
        Product oldProduct = productService.findById(productId);
        Product newProduct = ProductDTO.convertDTOToEntity(product);
        newProduct.setId(oldProduct.getId());
        return ResponseEntity.ok(ProductDTO.convertEntityToDTO(productService.save(newProduct)));
    }

    @PreAuthorize("hasAnyAuthority('MANAGER', 'ADMIN')")
    @DeleteMapping("/{productId}")
    ResponseEntity<?> delete(@PathVariable("productId") long productId) {
        productService.delete(productId);
        return ResponseEntity.noContent().build();
    }
}
