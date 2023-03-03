package com.store.store.repository;

import com.store.store.model.product.Review;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReviewRepository extends CrudRepository<Review, Long> {
    List<Review> findAllByAuthorId(Long id);

    List<Review> findAllByProductId(Long id);
}
