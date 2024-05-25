package com.example.demo.rest;


import com.example.demo.entity.Favorite;
import com.example.demo.model.request.UpdatePasswordRequest;
import com.example.demo.model.request.UpdateProfileUserRequest;
import com.example.demo.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserApi {
    @Autowired
    private UserService userService;

    @PutMapping("/update-profile")
    public ResponseEntity<?> updateProfile(@RequestBody UpdateProfileUserRequest request) {
        try {
            Integer userId = userService.getCurrentUserId();
            if (userId == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
            userService.updateUserProfile(userId, request.getName());
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/update-password")
    public ResponseEntity<?> updatePassword(@RequestBody UpdatePasswordRequest request) {
        try {
            Integer userId = userService.getCurrentUserId();

            if (userId == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }

            userService.updateUserPassword(userId, request);

            return ResponseEntity.ok("Password updated successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/favorites/add")
    public ResponseEntity<Map<String, Boolean>> addToFavorites(@RequestParam Integer userId, @RequestParam Integer movieId) {
        Map<String, Boolean> response = new HashMap<>();
        try {
            userService.addToFavorite(userId, movieId);
            response.put("added", true);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("added", false);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PostMapping("/favorites/remove")
    public ResponseEntity<Map<String, Boolean>> removeFromFavorites(@RequestParam Integer userId, @RequestParam Integer movieId) {
        Map<String, Boolean> response = new HashMap<>();
        try {
            userService.removeFromFavorites(userId, movieId);
            response.put("removed", true);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("removed", false);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

}
