package com.example.demo.rest;

import com.example.demo.entity.Episode;
import com.example.demo.entity.Movie;
import com.example.demo.entity.User;
import com.example.demo.model.enums.UserRole;
import com.example.demo.model.request.CreateEpisodeRequest;
import com.example.demo.model.request.CreateMovieRequest;
import com.example.demo.model.request.UpdateEpisodeRequest;
import com.example.demo.service.DashboardService;
import com.example.demo.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminApi {
    private final MovieService movieService;
    private final DashboardService dashboardService;

    @PostMapping("/movies/create")
    public ResponseEntity<?> createMovie(@RequestBody CreateMovieRequest request) {
        try {
            Movie movie = movieService.createMovie(request);
            return new ResponseEntity<>(movie, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/movies/episodes/create")
    public ResponseEntity<?> createEpisode(@RequestBody CreateEpisodeRequest request) {
        try {
            Episode episode = movieService.createEpisode(request);
            return new ResponseEntity<>(episode, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/movies/episodes/update")
    public ResponseEntity<?> updateEpisode(Integer id, @RequestBody UpdateEpisodeRequest request) {
        try {
            Episode episode = movieService.updateEpisode(id, request);
            return new ResponseEntity<>(episode, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/dashboard/data")
    public ResponseEntity<?> getDashboardData() {
        List<Movie> movieData = dashboardService.getMovieList(true);
        List<User> userData = dashboardService.getUserList(UserRole.USER);

        // Create a map to hold the data
        Map<String, Object> data = new HashMap<>();
        data.put("movies", movieData);
        data.put("users", userData);

        // Return the data as a ResponseEntity
        return ResponseEntity.ok(data);
    }
}
