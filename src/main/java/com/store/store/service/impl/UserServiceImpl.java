package com.store.store.service.impl;

import com.store.store.exception.NullEntityReferenceException;
import com.store.store.model.product.Review;
import com.store.store.model.user.User;
import com.store.store.repository.ReviewRepository;
import com.store.store.repository.UserRepository;
import com.store.store.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private static final String NOT_FOUND_MESSAGE = "User (id=%d) was not found";
    private static final String NULL_ENTITY_MESSAGE = "User cannot be 'null'";
    private static final String DELETED_MESSAGE = "User (id=%d) was deleted";
    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User create(User user) {
        if (user == null) {
            log.error(NULL_ENTITY_MESSAGE);
            throw new NullEntityReferenceException(NULL_ENTITY_MESSAGE);
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User update(User user) {
        if (user == null) {
            log.error(NULL_ENTITY_MESSAGE);
            throw new NullEntityReferenceException(NULL_ENTITY_MESSAGE);
        }
        return userRepository.save(user);
    }

    @Override
    public User findById(long id) {
        return userRepository.findById(id).orElseThrow(() -> {
            log.error(NOT_FOUND_MESSAGE.formatted(id));
            throw new EntityNotFoundException(NOT_FOUND_MESSAGE.formatted(id));
        });
    }

    @Override
    public void remove(long id) {
        userRepository.findById(id).ifPresentOrElse(
                p -> {
                    userRepository.delete(p);
                    log.info(DELETED_MESSAGE.formatted(id));
                }, () -> {
                    log.error(NOT_FOUND_MESSAGE.formatted(id));
                    throw new EntityNotFoundException(NOT_FOUND_MESSAGE.formatted(id));
                }
        );
    }

    //todo
    @Override
    public void makeUserRoleManager(User user) {

    }

    @Override
    public List<Review> findAllReviewsByUserId(long id) {
        return reviewRepository.findAllByAuthorId(id);
    }
}
