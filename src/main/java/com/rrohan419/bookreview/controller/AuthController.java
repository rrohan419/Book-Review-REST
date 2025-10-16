package com.rrohan419.bookreview.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.rrohan419.bookreview.dto.AuthRequest;
import com.rrohan419.bookreview.dto.AuthResponse;
import com.rrohan419.bookreview.dto.SignupUser;
import com.rrohan419.bookreview.model.User;
import com.rrohan419.bookreview.service.UserService;

import jakarta.validation.Valid;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) {

        AuthResponse token = userService.loginUser(authRequest);
        
        return ResponseEntity.ok(token);
    }

    @PostMapping("/signup")
    public ResponseEntity<User> signupUser(@RequestBody @Valid SignupUser signupUser) {
        return ResponseEntity.ok(userService.createUser(signupUser));
    }
}
