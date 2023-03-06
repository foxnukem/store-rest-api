package com.store.store.controller;

import com.store.store.dto.TokenResponse;
import com.store.store.dto.UserCredentialsDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/login")
public class LoginController {
    //todo
    @PostMapping
    TokenResponse login(@RequestBody UserCredentialsDTO userCredentials) {
        return null;
    }
}
