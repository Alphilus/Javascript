package com.example.demo.controller;

import com.example.demo.entity.Blog;
import com.example.demo.model.request.UpsertBlogRequest;
import com.example.demo.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("admin/blogs")
public class BlogController {
    @Autowired
    private BlogService blogService;

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

    @GetMapping("/detail")
    public String getBlogDetailPage(@RequestParam Integer id){
        return "admin/blog/detail";
    }

    @GetMapping("/create")
    public String getCreatePage() {
        return "admin/blog/create";
    }

    @PostMapping("/blogs")
    public Blog createBlog(@RequestBody Blog blog) {
        return blogService.saveBlog(blog);
    }
}
