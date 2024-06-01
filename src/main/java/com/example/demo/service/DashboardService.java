package com.example.demo.service;

import com.example.demo.entity.Blog;
import com.example.demo.entity.Movie;
import com.example.demo.entity.User;
import com.example.demo.model.enums.UserRole;
import com.example.demo.repository.BlogRepository;
import com.example.demo.repository.MovieRepository;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DashboardService {
    private final MovieRepository movieRepository;
    private final UserRepository userRepository;
    private final BlogRepository blogRepository;

    public List<Movie> getMovieList(Boolean status) {
        return movieRepository.findMovieByStatus(status);
    }

    public List<User> getUserList(UserRole role) {
        return userRepository.findByRole(role);
    }

    public List<Blog> getBlogList(Boolean status) {
        return blogRepository.findAllByStatus(status);
    }
}
