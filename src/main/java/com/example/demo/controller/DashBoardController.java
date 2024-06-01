package com.example.demo.controller;

import com.example.demo.entity.Blog;
import com.example.demo.entity.Movie;
import com.example.demo.entity.User;
import com.example.demo.model.enums.UserRole;
import com.example.demo.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("admin/dashboard")
@RequiredArgsConstructor
public class DashBoardController {
    private final DashboardService dashboardService;
    @GetMapping
    public String getDashBoardPage(Model model) {
        List<Movie> movie = dashboardService.getMovieList(true);
        List<User> users = dashboardService.getUserList(UserRole.USER);
        List<Blog> blogs = dashboardService.getBlogList(true);
        model.addAttribute("movies", movie);
        model.addAttribute("users", users);
        model.addAttribute("blogs", blogs);
        return "admin/dashboard/dashboard";
    }
}
