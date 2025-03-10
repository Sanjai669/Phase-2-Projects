package com.wipro.jwtsecurity.controller;

import com.wipro.jwtsecurity.util.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String username, @RequestParam String password) {
        if ("user".equals(username) && "password".equals(password)) {
            String token = JwtUtil.generateToken(username); // Generate JWT Token
            return ResponseEntity.ok(Map.of(
                "message", "Login Successful",
                "token", token
            ));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Invalid credentials"));
    }
}
