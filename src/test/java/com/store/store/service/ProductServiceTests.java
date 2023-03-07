package com.store.store.service;

import com.store.store.exception.NullEntityReferenceException;
import com.store.store.exception.UnacceptableParameterValueException;
import com.store.store.model.product.Category;
import com.store.store.model.product.Product;
import com.store.store.repository.CategoryRepository;
import com.store.store.repository.ProductRepository;
import com.store.store.service.impl.ProductServiceImpl;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTests {
    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    private Product product;
    private Category category;

    @BeforeEach
    void setup() {
        category = ServiceTestsUtils.buildTestCategory();
        product = ServiceTestsUtils.buildTestProduct(category);
    }

    @Test
    void whenProductIsNull_ThenThrowNullEntityReferenceException() {
        assertThrows(NullEntityReferenceException.class, () -> productService.save(null));
    }

    @Test
    void whenProductIsValidAndNewAndNewCategory_ThenSaveIt() {
        when(productRepository.save(any(Product.class))).thenReturn(product);
        assertEquals(product, productService.save(product));
        verify(productRepository).save(any(Product.class));
    }

    @Test
    void whenProductExists_ThenReturnIt() {
        when(productRepository.findById(anyLong())).thenReturn(Optional.of(product));
        assertEquals(product, productService.findById(1L));
        verify(productRepository).findById(any(long.class));
    }

    @Test
    void whenProductNotExist_ThenThrowEntityNotFoundException() {
        when(productRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> productService.findById(1L));
    }

    @Test
    void whenProductExists_ThenDeleteIt() {
        when(productRepository.findById(anyLong())).thenReturn(Optional.of(product));
        productService.delete(product.getId());
        verify(productRepository).findById(any(long.class));
    }

    @Test
    void whenDeletingProductNotExist_ThenThrowAnEntityNotFoundException() {
        when(productRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> productService.delete(9L));
        verify(productRepository).findById(any(long.class));
    }

    @Test
    void findAllProducts() {
        when(productRepository.findAll()).thenReturn(List.of(new Product(), new Product()));
        assertEquals(2, productService.findAll().size());
        verify(productRepository, atLeastOnce()).findAll();
    }

    @Test
    void findAllCategories() {
        when(categoryRepository.findAll()).thenReturn(List.of(new Category(), new Category()));
        assertEquals(2, productService.findAllCategories().size());
        verify(categoryRepository, atLeastOnce()).findAll();
    }

    @Test
    void whenPassNullOrEmptyCategoryName_ThenThrowUnacceptableParameterValueException() {
        assertThrows(UnacceptableParameterValueException.class, () -> productService.findAllProductsInCategory(null));
    }

    @Test
    void whenPassingCorrectCategoryNameButNoSuchProduct_ThenReturnAnEmptyList() {
        when(productRepository.findAllByCategoryName(anyString())).thenReturn(Collections.emptyList());
        assertTrue(productService.findAllProductsInCategory("a").isEmpty());
    }

    @Test
    void whenPassingCorrectCategoryName_ThenReturnListWithProducts() {
        when(productRepository.findAllByCategoryName(anyString())).thenReturn(List.of(new Product(), new Product(), new Product()));
        assertEquals(3, productService.findAllProductsInCategory("a").size());
    }

    @Test
    void whenPassNullOrEmptyCategoryNameFindByName_ThenThrowUnacceptableParameterValueException() {
        assertThrows(UnacceptableParameterValueException.class, () -> productService.findByName(null));
    }

    @Test
    void whenPassingCorrectCategoryNameButNoSuchProduct_ThenThrowEntityNotFoundException() {
        assertThrows(EntityNotFoundException.class, () -> productService.findByName("some"));
    }

    @Test
    void whenPassingCorrectCategoryName_ThenReturnCategoryInstance() {
        when(categoryRepository.findByName(anyString())).thenReturn(Optional.of(category));
        assertEquals(category, productService.findByName("cat"));
    }

}
