package com.store.store.controller;

import com.store.store.dto.UserDTO;
import com.store.store.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;

    @GetMapping
    ResponseEntity<List<UserDTO>> getAll() {
        var users = userService.findAll().stream()
                .map(UserDTO::convertEntityToDTO)
                .toList();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{userId}")
    ResponseEntity<UserDTO> getUserById(@PathVariable("userId") long userId) {
        var user = UserDTO.convertEntityToDTO(userService.findById(userId));
        return ResponseEntity.ok(user);
    }

    //todo
    @PostMapping
    ResponseEntity<UserDTO> addNewUser(@RequestBody UserDTO user) {
        return null;
    }

    //todo
    @RequestMapping(path = "/{userId}", method = {RequestMethod.PUT, RequestMethod.PATCH})
    ResponseEntity<UserDTO> updateUser(@PathVariable("userId") long userId, @RequestBody UserDTO user) {
        return null;
    }
}
