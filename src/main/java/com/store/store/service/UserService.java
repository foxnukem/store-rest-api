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
     * @param user new or existing instance {@link User}
     * @return merged {@link User} instance
     */
    User save(User user);

    /**
     * Finds {@link User} by id. If there is no record with such id, throws {@link EntityNotFoundException}.
     *
     * @param id user's id
     * @return {@link User} instance
     */
    User findById(long id);


    /**
     * Changes {@link User}'s role from USER to MANAGER.
     *
     * @param id user's id
     */
    void makeUserRoleManager(long id);

    /**
     * Finds all reviews of user
     *
     * @param id user's id
     * @return {@link List<Review>} instance with reviews of user
     */
    List<Review> findAllReviewsByUserId(long id);
}
