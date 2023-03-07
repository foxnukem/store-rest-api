package com.store.store.controller;

import com.store.store.dto.TokenResponse;
import com.store.store.dto.UserCredentialsDTO;
import com.store.store.security.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class LoginController {
    private final AuthenticationService service;

    @PostMapping("/login")
    ResponseEntity<TokenResponse> login(@RequestBody UserCredentialsDTO userCredentials) {
        return ResponseEntity.ok(service.authenticate(userCredentials));
    }
}
