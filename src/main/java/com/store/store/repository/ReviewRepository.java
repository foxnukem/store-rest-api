package com.store.store.repository;

import com.store.store.model.product.Review;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface ReviewRepository extends ListCrudRepository<Review, Long> {
    List<Review> findAllByAuthorId(Long id);

    List<Review> findAllByProductId(Long id);
}
