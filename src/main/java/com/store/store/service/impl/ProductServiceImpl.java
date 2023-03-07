package com.store.store.service.impl;

import com.store.store.exception.NullEntityReferenceException;
import com.store.store.exception.UnacceptableParameterValueException;
import com.store.store.model.product.Category;
import com.store.store.model.product.Product;
import com.store.store.repository.CategoryRepository;
import com.store.store.repository.ProductRepository;
import com.store.store.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private static final String PRODUCT_NOT_FOUND_MESSAGE = "Product (id=%d) was not found";
    private static final String CATEGORY_NOT_FOUND_MESSAGE = "Category (name=%s) was not found";
    private static final String PRODUCT_NULL_ENTITY_MESSAGE = "Product cannot be 'null'";
    private static final String PRODUCT_DELETED_MESSAGE = "Product (id=%d) was deleted";

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public Product save(Product product) {
        if (product == null) {
            log.error(PRODUCT_NULL_ENTITY_MESSAGE);
            throw new NullEntityReferenceException(PRODUCT_NULL_ENTITY_MESSAGE);
        }
        return productRepository.save(product);
    }

    @Override
    public Product findById(long id) {
        return productRepository.findById(id).orElseThrow(() -> {
            log.error(PRODUCT_NOT_FOUND_MESSAGE.formatted(id));
            throw new EntityNotFoundException(PRODUCT_NOT_FOUND_MESSAGE.formatted(id));
        });
    }

    @Override
    public void delete(long id) {
        productRepository.findById(id).ifPresentOrElse(
                p -> {
                    productRepository.delete(p);
                    log.info(PRODUCT_DELETED_MESSAGE.formatted(id));
                }, () -> {
                    log.error(PRODUCT_NOT_FOUND_MESSAGE.formatted(id));
                    throw new EntityNotFoundException(PRODUCT_NOT_FOUND_MESSAGE.formatted(id));
                }
        );
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> findAllProductsInCategory(String category) {
        if (category == null || category.isEmpty()) {
            throw new UnacceptableParameterValueException("Category name is null or empty");
        }
        return productRepository.findAllByCategoryName(category);
    }

    @Override
    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findByName(String name) {
        if (name == null || name.isEmpty()) {
            throw new UnacceptableParameterValueException("Category name is null or empty");
        }
        return categoryRepository.findByName(name.toLowerCase()).orElseThrow(() -> {
            log.error(CATEGORY_NOT_FOUND_MESSAGE.formatted(name.toLowerCase()));
            throw new EntityNotFoundException(CATEGORY_NOT_FOUND_MESSAGE.formatted(name.toLowerCase()));
        });
    }
}
