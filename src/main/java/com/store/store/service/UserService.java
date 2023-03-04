package com.store.store.service;

import com.store.store.exception.NullEntityReferenceException;
import com.store.store.model.product.Review;
import com.store.store.model.user.User;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

public interface UserService {
    /**
     * Adds new {@link User} and encrypts password. Throws {@link NullEntityReferenceException} if pass null.
     *
     * @param user
     * @return merged {@link User} instance
     */
    User create(User user);

    /**
     * Updates field of existing {@link User} entity. Throws {@link NullEntityReferenceException} if pass null. If there is no such record, throws {@link EntityNotFoundException}.
     *
     * @param user
     * @return merged {@link User} instance
     */
    User update(User user);

    /**
     * Finds {@link User} by id. If there is no record with such id, throws {@link EntityNotFoundException}.
     *
     * @param id
     * @return {@link User} instance
     */
    User findById(long id);

    /**
     * Removes {@link User} by id. If there is no record with such id, throws {@link EntityNotFoundException}.
     *
     * @param id
     */
    void remove(long id);

    /**
     * Changes {@link User}'s role from USER to MANAGER.
     *
     * @param user
     */
    void makeUserRoleManager(User user);

    /**
     * Finds all reviews of user
     *
     * @param id
     * @return {@link List<Review>} instance with reviews of user
     */
    List<Review> findAllReviewsByUserId(long id);
}
