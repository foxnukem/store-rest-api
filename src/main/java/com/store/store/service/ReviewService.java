package com.store.store.service;

import com.store.store.exception.NullEntityReferenceException;
import com.store.store.model.product.Product;
import com.store.store.model.product.Review;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

public interface ReviewService {
    /**
     * Finds reviews of the {@link Product}.
     *
     * @param id id of the {@link Product}
     * @return {@link List} with reviews of the {@link Product}
     */
    List<Review> findAllReviewsByProductId(long id);

    /**
     * Saves new or updated {@link Review} entity.
     *
     * @param review {@link Review} instance with set author and product
     * @return merged {@link Review} entity
     * @throws NullEntityReferenceException if pass null
     */
    Review saveReview(Review review);

    /**
     * Finds {@link Review} by id.
     *
     * @param id id of the {@link Review}
     * @return {@link Review} instance
     * @throws EntityNotFoundException if there is no record with such id
     */
    Review findById(long id);

    /**
     * Removes {@link Review} by id.
     *
     * @param id id of the {@link Review}
     * @throws EntityNotFoundException if there is no record with such id
     */
    void removeReview(long id);
}
