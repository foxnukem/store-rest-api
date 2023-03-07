package com.store.store.service.impl;

import com.store.store.exception.NullEntityReferenceException;
import com.store.store.model.product.Review;
import com.store.store.repository.ReviewRepository;
import com.store.store.service.ReviewService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private static final String REVIEW_NOT_FOUND_MESSAGE = "Review (id=%d) was not found";
    private static final String REVIEW_NULL_ENTITY_MESSAGE = "Review cannot be 'null'";
    private static final String REVIEW_DELETED_MESSAGE = "Review (id=%d) was deleted";

    private final ReviewRepository reviewRepository;

    @Override
    public List<Review> findAllReviewsByProductId(long id) {
        return reviewRepository.findAllByProductId(id);
    }

    @Override
    public Review save(Review review) {
        if (review == null) {
            log.error(REVIEW_NULL_ENTITY_MESSAGE);
            throw new NullEntityReferenceException(REVIEW_NULL_ENTITY_MESSAGE);
        }
        return reviewRepository.save(review);
    }

    @Override
    public Review findById(long id) {
        return reviewRepository.findById(id).orElseThrow(() -> {
            log.error(REVIEW_NOT_FOUND_MESSAGE.formatted(id));
            throw new EntityNotFoundException(REVIEW_NOT_FOUND_MESSAGE.formatted(id));
        });
    }

    @Override
    public void delete(long id) {
        reviewRepository.findById(id).ifPresentOrElse(
                r -> {
                    reviewRepository.delete(r);
                    log.info(REVIEW_DELETED_MESSAGE.formatted(id));
                }, () -> {
                    log.error(REVIEW_NOT_FOUND_MESSAGE.formatted(id));
                    throw new EntityNotFoundException(REVIEW_NOT_FOUND_MESSAGE.formatted(id));
                });
    }
}
