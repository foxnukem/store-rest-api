package com.store.store.service.impl;

import com.store.store.exception.NullEntityReferenceException;
import com.store.store.model.product.Review;
import com.store.store.model.user.Role;
import com.store.store.model.user.User;
import com.store.store.repository.ReviewRepository;
import com.store.store.repository.UserRepository;
import com.store.store.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private static final String NOT_FOUND_MESSAGE = "User (id=%d) was not found";
    private static final String NULL_ENTITY_MESSAGE = "User cannot be 'null'";
    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;

    @Override
    public User save(User user) {
        checkIfUserIsNotNull(user);
        return userRepository.save(user);
    }

    private void checkIfUserIsNotNull(User user) {
        if (user == null) {
            log.error(NULL_ENTITY_MESSAGE);
            throw new NullEntityReferenceException(NULL_ENTITY_MESSAGE);
        }
    }

    @Override
    public User findById(long id) {
        return userRepository.findById(id).orElseThrow(() -> {
            log.error(NOT_FOUND_MESSAGE.formatted(id));
            throw new EntityNotFoundException(NOT_FOUND_MESSAGE.formatted(id));
        });
    }

    @Override
    public void makeUserRoleManager(long id) {
        userRepository.findById(id).ifPresentOrElse(u -> {
                    if (u.getRole().equals(Role.USER)) {
                        u.setRole(Role.MANAGER);
                    }
                }, () -> {
                    log.error(NOT_FOUND_MESSAGE.formatted(id));
                    throw new EntityNotFoundException(NOT_FOUND_MESSAGE.formatted(id));
                }
        );
    }

    @Override
    public List<Review> findAllReviewsByUserId(long id) {
        return reviewRepository.findAllByAuthorId(id);
    }
}
