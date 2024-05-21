package com.example.demo.controller;

import com.example.demo.entity.*;
import com.example.demo.model.enums.MovieType;
import com.example.demo.service.BlogService;
import com.example.demo.service.CommentService;
import com.example.demo.service.MovieService;
import com.example.demo.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class WebController {
    @Autowired
    private MovieService movieService;

    @Autowired
    private BlogService blogService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private ReviewService reviewService;

    @GetMapping("/")
    public String getHomePage(Model model,
                              @RequestParam(required = false, defaultValue = "1") int page,
                              @RequestParam(required = false, defaultValue = "12") int pageSize){
        Page<Movie> pageData = movieService.GetMovieByStatus(true, page, pageSize);
        List<Movie> hotMovies = movieService.getHotMovies(true);
        model.addAttribute("hotMovies", hotMovies);
        model.addAttribute("pageData", pageData);
        model.addAttribute("currentPage", page);
        return "/html/index";
    }
    @GetMapping("/phim-bo")
    public String getPhimBoPage(Model model,
                                @RequestParam(required = false, defaultValue = "1") int page,
                                @RequestParam(required = false, defaultValue = "12") int pageSize) {
        Page<Movie> pageData = movieService.getMovieByType(MovieType.PHIM_BO, true, page, pageSize);
        model.addAttribute("pageData", pageData);
        model.addAttribute("currentPage", page);
        return "/html/phim-bo";
    }

    @GetMapping("/phim-le")
    public String phimLe(Model model,
                         @RequestParam(required = false, defaultValue = "1") int page,
                         @RequestParam(required = false, defaultValue = "12") int pageSize) {
        Page<Movie> pageData = movieService.getMovieByType(MovieType.PHIM_LE, true, page, pageSize);
        model.addAttribute("pageData", pageData);
        model.addAttribute("currentPage", page);
        return "/html/phim-le";
    }

    @GetMapping("/phim-chieu-rap")
    public String phimChieuRap(Model model,
                               @RequestParam(required = false, defaultValue = "1") int page,
                               @RequestParam(required = false, defaultValue = "12") int pageSize) {
        Page<Movie> pageData = movieService.getMovieByType(MovieType.PHIM_CHIEU_RAP, true, page, pageSize);
        model.addAttribute("pageData", pageData);
        model.addAttribute("currentPage", page);
        return "/html/phim-chieu-rap";
    }

    @GetMapping("/tin-tuc")
    public String tinTuc(Model model,
                        @RequestParam(required = false, defaultValue = "1") int page,
                        @RequestParam(required = false, defaultValue = "12") int pageSize) {
        Page<Blog> pageData = blogService.getBlogByStatus(true, page, pageSize);
        model.addAttribute("pageData", pageData);
        model.addAttribute("currentPage", page);
        return "/html/tin-tuc";
    }

    @GetMapping("/tin-tuc/{id}/{slug}")
    public String tinTucChiTiet(Model model, @PathVariable String slug, @PathVariable Integer id) {
        Blog blog = blogService.getBlogDetails(slug, id);
        List<Comment> comments = commentService.getCommentsByBlogId(id);
        model.addAttribute("blog", blog);
        model.addAttribute("comments", comments);
        return "/html/tin-tuc-chi-tiet";
    }
    @GetMapping("/phim/{id}/{slug}")
    public String phimChiTiet(Model model, @PathVariable String slug, @PathVariable Integer id) {
        Movie movie = movieService.getMovieById(id, slug);

        if (movie.getType() == MovieType.PHIM_BO) {
            List<Episode> episodes = movieService.getEpisodeByMovieId(movie.getId());
            model.addAttribute("episodes", episodes);
        }

        List<Review> reviews = reviewService.getReviewsByMovie(id);
        List<String> genreNames = movie.getGenres().stream()
                .map(Genre::getName)
                .toList();
        List<Movie> movies = movieService.getRecommendedMovie(genreNames, movie.getId());
        model.addAttribute("movie", movie);
        model.addAttribute("reviews", reviews);
        model.addAttribute("recommendedMovies", movies);

        return "/html/detail";
    }

    @GetMapping("/dang-nhap")
    public String getLoginPage() {
        return "html/login";
    }
}
