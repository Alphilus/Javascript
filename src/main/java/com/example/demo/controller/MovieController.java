package com.example.demo.controller;

import com.example.demo.entity.Movie;
import com.example.demo.model.enums.MovieType;
import com.example.demo.repository.*;
import com.example.demo.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    private final EpisodeRepository episodeRepository;

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

    @GetMapping("/{id}")
    public String getDetailPage(Model model, @PathVariable Integer id) {
        model.addAttribute("movie", movieService.getMovieByIdOnly(id));
        model.addAttribute("countries", countryRepository.findAll());
        model.addAttribute("directors", directorRepository.findAll());
        model.addAttribute("actors", actorRepository.findAll());
        model.addAttribute("genres", genreRepository.findAll());
        model.addAttribute("movieTypes", MovieType.values());

        //Trả về ds tập phim của movie(sắp xếp theo order tăng dần)
        model.addAttribute("episodes", episodeRepository.findByMovie_IdOrderByDisplayOrder(id));
        return "admin/movie/detail";
    }
}
