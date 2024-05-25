package com.example.demo.controller;

import com.example.demo.entity.Blog;
import com.example.demo.entity.User;
import com.example.demo.model.request.UpsertBlogRequest;
import com.example.demo.service.BlogService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("admin/blogs")
@RequiredArgsConstructor
public class BlogController {
    @Autowired
    private BlogService blogService;

    private final HttpSession session;

    @GetMapping
    public String getIndexPage(Model model) {
        List<Blog> blogs = blogService.getAllBlog();
        model.addAttribute("blogs", blogs);
        return "admin/blog/index";
    }

    @GetMapping("/own-blogs")
    public String getOwnBlogPage(Model model){
        model.addAttribute("blogs", blogService.getOwnBlog());
        return "admin/blog/own";
    }

    @GetMapping("/{id}")
    public String getBlogDetailPage(@PathVariable Integer id, Model model) {
        Blog blog = blogService.getOwnBlogDetails(id);
        model.addAttribute("blogs", blog);
        return "admin/blog/detail";
    }

    @GetMapping("/create")
    public String getCreatePage() {
        return "admin/blog/create";
    }

//    @PostMapping("/blogs")
//    public Blog createBlog(@RequestBody Blog blog) {
//        return blogService.saveBlog(blog);
//    }
}
