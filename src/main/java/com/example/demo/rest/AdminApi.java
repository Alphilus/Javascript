package com.example.demo.rest;

import com.example.demo.entity.Movie;
import com.example.demo.model.request.CreateMovieRequest;
import com.example.demo.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminApi {
    private final MovieService movieService;

    @PostMapping("/movies/create")
    public ResponseEntity<?> createMovie(@RequestBody CreateMovieRequest request) {
        try {
            Movie movie = movieService.createMovie(request);
            return new ResponseEntity<>(movie, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
