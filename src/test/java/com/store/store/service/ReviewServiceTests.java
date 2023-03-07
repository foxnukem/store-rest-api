package com.store.store.service;

import com.store.store.exception.NullEntityReferenceException;
import com.store.store.model.product.Product;
import com.store.store.model.product.Review;
import com.store.store.repository.ReviewRepository;
import com.store.store.service.impl.ReviewServiceImpl;
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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReviewServiceTests {
    @Mock
    private ReviewRepository reviewRepository;

    @InjectMocks
    private ReviewServiceImpl reviewService;

    private Review review;
    private Product product;

    @BeforeEach
    void setup() {
        var user = ServiceTestsUtils.buildTestUser();
        var category = ServiceTestsUtils.buildTestCategory();
        product = ServiceTestsUtils.buildTestProduct(category);
        review = ServiceTestsUtils.buildTestReview(user, product);
    }

    @Test
    void whenReviewIsNull_ThenThrowNullEntityReferenceException() {
        assertThrows(NullEntityReferenceException.class, () -> reviewService.save(null));
    }

    @Test
    void whenReviewIsValidAndNew_ThenSaveIt() {
        when(reviewRepository.save(any(Review.class))).thenReturn(review);
        assertEquals(review, reviewService.save(review));
        verify(reviewRepository).save(any(Review.class));
    }

    @Test
    void whenReviewExists_ThenReturnIt() {
        when(reviewRepository.findById(anyLong())).thenReturn(Optional.of(review));
        assertEquals(review, reviewService.findById(1L));
        verify(reviewRepository).findById(any(long.class));
    }

    @Test
    void whenReviewNotExist_ThenThrowEntityNotFoundException() {
        when(reviewRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> reviewService.findById(1L));
    }

    @Test
    void whenReviewExists_ThenDeleteIt() {
        when(reviewRepository.findById(anyLong())).thenReturn(Optional.of(review));
        reviewService.delete(review.getId());
        verify(reviewRepository).findById(any(long.class));
    }

    @Test
    void whenDeletingReviewNotExist_ThenThrowAnEntityNotFoundException() {
        when(reviewRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> reviewService.delete(9L));
        verify(reviewRepository).findById(any(long.class));
    }

    @Test
    void whenFindAllReviewsByProductIdWhichNotExist_ThenReturnAnEmptyList() {
        when(reviewRepository.findAllByProductId(anyLong())).thenReturn(Collections.emptyList());
        assertTrue(reviewRepository.findAllByProductId(product.getId()).isEmpty());
    }

    @Test
    void whenFindAllReviewsByProductId_ThenReturnAList() {
        when(reviewRepository.findAllByProductId(anyLong())).thenReturn(List.of(new Review(), new Review()));
        assertEquals(2, reviewService.findAllReviewsByProductId(product.getId()).size());
    }

}
