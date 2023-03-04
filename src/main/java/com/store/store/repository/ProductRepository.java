package com.store.store.repository;

import com.store.store.model.product.Product;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface ProductRepository extends ListCrudRepository<Product, Long> {
    List<Product> findAllByCategoryName(String name);
}
