package com.store.store.service;

import com.store.store.exception.NullEntityReferenceException;
import com.store.store.exception.UnacceptableParameterValueException;
import com.store.store.model.product.Category;
import com.store.store.model.product.Product;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

public interface ProductService {
    /**
     * Saves new or updated {@link Product} entity.
     *
     * @param product {@link Product} instance
     * @return merged {@link Product} entity
     * @throws NullEntityReferenceException if pass null
     */
    Product save(Product product);

    /**
     * Finds {@link Product} by id.
     *
     * @param id id of the {@link Product}
     * @return {@link Product} instance
     * @throws EntityNotFoundException if there is no record with such id
     */
    Product findById(long id);

    /**
     * Removes {@link Product} by id.
     *
     * @param id id of the {@link Product}
     * @throws EntityNotFoundException if there is no record with such id
     */
    void delete(long id);

    /**
     * Finds all existing products.
     *
     * @return {@link List<Product>} with {@link Product} instances if there are ones
     */
    List<Product> findAll();

    /**
     * Finds all existing products of passed category.
     *
     * @param category category's name
     * @return {@link List<Product>} with {@link Product} instances if there are ones
     */
    List<Product> findAllProductsInCategory(String category);

    /**
     * Finds all existing categories.
     *
     * @return {@link List<Category>} with {@link Category} instances
     * @throws UnacceptableParameterValueException if pass null or an empty string
     */
    List<Category> findAllCategories();
}
