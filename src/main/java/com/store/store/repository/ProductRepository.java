package com.store.store.repository;

import com.store.store.model.product.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> {
    List<Product> findAllByCategoryName(String name);
}
