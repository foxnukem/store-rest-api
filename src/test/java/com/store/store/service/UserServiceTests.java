package com.store.store.service;

import com.store.store.exception.NullEntityReferenceException;
import com.store.store.model.product.Review;
import com.store.store.model.user.Role;
import com.store.store.model.user.User;
import com.store.store.repository.ReviewRepository;
import com.store.store.repository.UserRepository;
import com.store.store.service.impl.UserServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests {
    @Mock
    private UserRepository userRepository;

    @Mock
    private ReviewRepository reviewRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private User user;

    @BeforeEach
    void setup() {
        user = new User();
        user.setId(1L);
        user.setEmail("mark@gmail.com");
        user.setFirstName("Mark");
        user.setLastName("Huang");
        user.setPassword("markPassword8");
        user.setRole(Role.USER);
    }

    @Test
    void whenUserIsNull_ThenThrowNullEntityReferenceException() {
        assertThrows(NullEntityReferenceException.class, () -> userService.save(null));
    }

    @Test
    void whenUserIsValid_ThenSaveIt() {
        when(userRepository.save(any(User.class))).thenReturn(user);

        assertEquals(user, userService.save(user));

        verify(userRepository).save(any(User.class));
    }

    @Test
    void whenUserExists_ThenReturnIt() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        assertEquals(user, userService.findById(1L));
    }

    @Test
    void whenUserNotExist_ThenThrowEntityNotFoundException() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> userService.findById(1L));
    }

    @Test
    void whenUserHasRoleUser_ThenChangeItToManager() {
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        userService.makeUserRoleManager(1L);
        assertEquals(Role.MANAGER, user.getRole());
    }

    @Test
    void whenUserIsAlreadyManager_ThenDoNothing() {
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        user.setRole(Role.MANAGER);
        userService.makeUserRoleManager(1L);
        verify(userRepository, times(0)).save(any(User.class));
    }

    @Test
    void whenUserNotExistsAndWannaBeManager_ThenThrowsEntityNotFoundException() {
        assertThrows(EntityNotFoundException.class, () -> userService.makeUserRoleManager(1L));
    }

    @Test
    void whenUserHasNoReviews_ThenReturnsAnEmptyList() {
        assertTrue(reviewRepository.findAllByAuthorId(user.getId()).isEmpty());
    }

    @Test
    void whenUserHasReviews_ThenReturnListWithThem() {
        Review r1 = new Review();
        Review r2 = new Review();
        when(reviewRepository.findAllByAuthorId(anyLong())).thenReturn(List.of(r1, r2));
        assertEquals(2, userService.findAllReviewsByUserId(1L).size());
    }

}
