package com.wipro.jwtsecurity.controller;

import com.wipro.jwtsecurity.util.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ProtectedResourceController {

    @GetMapping("/protected-resource")
    public ResponseEntity<?> getProtectedResource(@RequestHeader("Authorization") String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Missing or invalid token");
        }

        String token = authHeader.substring(7); // Remove "Bearer " prefix
        String username = JwtUtil.validateToken(token); // Validate token

        if (username != null) {
            return ResponseEntity.ok("Login Successful! You have accessed a protected resource.");
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid or expired token");
        }
    }
}
