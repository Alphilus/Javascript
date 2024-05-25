package com.example.demo.repository;

import com.example.demo.entity.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog, Integer> {
    List<Blog> findAll(Sort createdAt);
    Blog findUserBlogById(Integer id);
    Page<Blog> findBlogByStatus(Boolean status, Pageable pageable);
    List<Blog> findByUser_IdOrderByCreatedAtDesc(Integer id);
    Blog findBySlugAndId(String slug, Integer id);

}
