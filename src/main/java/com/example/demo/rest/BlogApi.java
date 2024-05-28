package com.example.demo.rest;

import com.example.demo.entity.Blog;
import com.example.demo.model.request.UpsertBlogRequest;
import com.example.demo.service.BlogService;
import com.example.demo.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/admin/blogs")
@RequiredArgsConstructor
public class BlogApi {
    @Autowired
    public BlogService blogService;
    @Autowired
    public UserService userService;

    @PostMapping("/update")
    public ResponseEntity<?> updateBlog(@Valid @RequestBody UpsertBlogRequest request) {
        try {
            Integer userId = userService.getCurrentUserId();
            if (userId == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
            blogService.updateBlog(userId, request);
            return ResponseEntity.ok("Blog updated successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/{id}/upload-thumbnail")
    public ResponseEntity<?> uploadThumbnail(@PathVariable Integer id, @RequestParam MultipartFile file) {
        return ResponseEntity.ok(blogService.uploadThumbnail(id, file));
    }
}
