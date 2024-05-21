package com.example.demo.service;

import com.example.demo.entity.Blog;
import com.example.demo.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService {
    @Autowired
    private BlogRepository blogRepository;

    public List<Blog> getBlogById(Integer id) {
        return blogRepository.findBlogById(id);
    }

    public Page<Blog> getBlogByStatus(Boolean status,int page, int pageSize) {
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by("createdAt").descending());
        return blogRepository.findBlogByStatus(status, pageRequest);
    }

    public Blog getBlogDetails(String slug, Integer id) {
        return blogRepository.findBySlugAndId(slug, id);
    }
}
