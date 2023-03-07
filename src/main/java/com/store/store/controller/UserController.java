package com.store.store.controller;

import com.store.store.dto.UserDTO;
import com.store.store.model.user.User;
import com.store.store.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("hasAuthority('ADMIN') or #userId == authentication.principal.id")
    @GetMapping("/{userId}")
    ResponseEntity<UserDTO> getUserById(@PathVariable("userId") long userId) {
        var user = UserDTO.convertEntityToDTO(userService.findById(userId));
        return ResponseEntity.ok(user);
    }

    @PostMapping("/create")
    ResponseEntity<UserDTO> addNewUser(@RequestBody UserDTO user) {
        User newUser = UserDTO.convertDTOToEntity(user);
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        return ResponseEntity.ok(UserDTO.convertEntityToDTO(userService.save(newUser)));
    }

    @PreAuthorize("hasAuthority('ADMIN') or #userId == authentication.principal.id")
    @RequestMapping(path = "/{userId}", method = {RequestMethod.PUT, RequestMethod.PATCH})
    ResponseEntity<UserDTO> updateUser(@PathVariable("userId") long userId, @RequestBody UserDTO user) {
        User oldUser = userService.findById(userId);
        User userEntity = UserDTO.convertDTOToEntity(user);
        userEntity.setId(oldUser.getId());
        userEntity.setAddress(oldUser.getAddress());
        return ResponseEntity.ok(UserDTO.convertEntityToDTO(userService.save(userEntity)));
    }
}
