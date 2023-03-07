package com.store.store.service;

import com.store.store.model.product.Category;
import com.store.store.model.product.Product;
import com.store.store.model.product.Review;
import com.store.store.model.user.Role;
import com.store.store.model.user.User;

import java.math.BigDecimal;

class ServiceTestsUtils {

    static User buildTestUser() {
        var user = new User();
        user.setId(1L);
        user.setEmail("mark@gmail.com");
        user.setFirstName("Mark");
        user.setLastName("Huang");
        user.setPassword("markPassword8");
        user.setRole(Role.USER);
        return user;
    }

    static Product buildTestProduct(Category category) {
        var product = new Product();
        product.setId(1L);
        product.setTitle("some product");
        product.setPrice(BigDecimal.ONE);
        product.setCategory(category);
        product.setImageUrl("some_url");
        product.setDescription("");
        return product;
    }

    static Category buildTestCategory() {
        var category = new Category();
        category.setId(1L);
        category.setName("cat");
        return category;
    }

    static Review buildTestReview(User user, Product product) {
        var review = new Review();
        review.setAuthor(user);
        review.setProduct(product);
        review.setRating(4.0);
        review.setText("some text");
        return review;
    }
}
