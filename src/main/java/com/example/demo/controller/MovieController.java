package com.example.demo.controller;

import com.example.demo.entity.Movie;
import com.example.demo.model.enums.MovieType;
import com.example.demo.repository.ActorRepository;
import com.example.demo.repository.CountryRepository;
import com.example.demo.repository.DirectorRepository;
import com.example.demo.repository.GenreRepository;
import com.example.demo.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("admin/movies")
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;
    private final CountryRepository countryRepository;
    private final DirectorRepository directorRepository;
    private final ActorRepository actorRepository;
    private final GenreRepository genreRepository;

    @GetMapping
    public String homePage(Model model){
        List<Movie> movies = movieService.getAllMovies();
        model.addAttribute("movies", movies);
        return "admin/movie/index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        //Trả về ds quốc gia, đạo diễn, diễn viên, thể loại, loại phim
        //Todo: Refactor theo controller - service - repo
        model.addAttribute("countries", countryRepository.findAll());
        model.addAttribute("directors", directorRepository.findAll());
        model.addAttribute("actors", actorRepository.findAll());
        model.addAttribute("genres", genreRepository.findAll());
        model.addAttribute("movieTypes", MovieType.values());
        return "admin/movie/create";
    }
}
