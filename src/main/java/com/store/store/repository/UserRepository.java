package com.store.store.repository;

import com.store.store.model.user.User;
import org.springframework.data.repository.ListCrudRepository;

import java.util.Optional;

public interface UserRepository extends ListCrudRepository<User, Long> {
    Optional<User> findUserByEmail(String email);
}
