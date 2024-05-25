package com.example.demo.service;

import com.example.demo.entity.Blog;
import com.example.demo.entity.User;
import com.example.demo.model.request.UpsertBlogRequest;
import com.example.demo.repository.BlogRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogService {
    @Autowired
    private BlogRepository blogRepository;
    private final HttpSession session;

    public Page<Blog> getBlogByStatus(Boolean status,int page, int pageSize) {
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by("createdAt").descending());
        return blogRepository.findBlogByStatus(status, pageRequest);
    }

    public Blog getBlogDetails(String slug, Integer id) {
        return blogRepository.findBySlugAndId(slug, id);
    }

    public List<Blog> getAllBlog() {
        return blogRepository.findAll(Sort.by("createdAt").descending());
    }

    public List<Blog> getOwnBlog() {
        User user = (User) session.getAttribute("currentUser");
        if (user != null) {
            return blogRepository.findByUser_IdOrderByCreatedAtDesc(user.getId());
        } else {
            return new ArrayList<>(); // Return an empty list if no user is found in the session
        }
    }

    public Blog saveBlog(Blog blog){
        LocalDateTime now = LocalDateTime.now();
        blog.setCreatedAt(now);
        blog.setUpdatedAt(now);

        return blogRepository.save(blog);
    }
}
