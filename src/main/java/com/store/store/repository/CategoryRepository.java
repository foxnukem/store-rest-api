package com.store.store.repository;

import com.store.store.model.product.Category;
import org.springframework.data.repository.ListCrudRepository;

import java.util.Optional;

public interface CategoryRepository extends ListCrudRepository<Category, Long> {
    Optional<Category> findByName(String name);
}
