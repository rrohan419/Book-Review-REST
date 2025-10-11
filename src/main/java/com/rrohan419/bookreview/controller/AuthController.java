package com.rrohan419.bookreview.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.rrohan419.bookreview.dto.AuthRequest;
import com.rrohan419.bookreview.dto.AuthResponse;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private static final Map<String, String> credsToToken = Map.of(
            "admin:admin123", "token-admin",
            "reader:reader123", "token-reader"
    );

    private static final Map<String, String> tokenToRole = Map.of(
            "token-admin", "ADMIN",
            "token-reader", "READER"
    );

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest req) {
        String key = req.getUsername() + ":" + req.getPassword();
        String token = credsToToken.get(key);
        if (token == null) {
            return ResponseEntity.status(401).body(Map.of("error", "Invalid credentials"));
        }
        String role = tokenToRole.get(token);
        return ResponseEntity.ok(new AuthResponse(token, role));
    }
}
