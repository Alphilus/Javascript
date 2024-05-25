package com.example.demo.rest;

import com.example.demo.entity.Review;
import com.example.demo.model.request.LoginRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthApi {
    private final AuthService authService;
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request){
        authService.login(request);
        return ResponseEntity.ok("Login successful");
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        // Invalidate the session to clear all session attributes
        request.getSession().invalidate();
        return ResponseEntity.ok("Logout successful");
    }

    @PostMapping("/reviews")
    public ResponseEntity<?> createReview(@Valid @RequestBody Review review, BindingResult result) {
        if (result.hasErrors()) {
            // Return validation errors
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }
        // Process the review
        return ResponseEntity.ok("Review created successfully");
    }
}
