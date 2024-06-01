package com.example.demo.controller;

import com.example.demo.entity.*;
import com.example.demo.model.enums.MovieType;
import com.example.demo.service.*;
import jakarta.servlet.http.HttpSession;
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

    @Autowired
    private UserService userService;
    @Autowired
    private EpisodeService episodeService;


    @GetMapping("/")
    public String getHomePage(Model model,
                              @RequestParam(required = false, defaultValue = "1") int page,
                              @RequestParam(required = false, defaultValue = "12") int pageSize){
        Page<Movie> pageData = movieService.GetMovieByStatus(true, page, pageSize);
        List<Movie> hotMovies = movieService.getHotMovies(true, 1, 8).getContent();
        List<Movie> listPhimBo = movieService.getMoviesByType(MovieType.PHIM_BO);
        List<Movie> listPhimLe = movieService.getMoviesByType(MovieType.PHIM_LE);
        List<Movie> listPhimChieuRap = movieService.getMoviesByType(MovieType.PHIM_CHIEU_RAP);
        model.addAttribute("listPhimBo", listPhimBo);
        model.addAttribute("listPhimLe", listPhimLe);
        model.addAttribute("listPhimChieuRap", listPhimChieuRap);
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

        if (movie == null) {
            // Handle the case when movie is not found
            return "redirect:/not-found";  // or another appropriate view
        }

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

    // http://localhost:8080/xem-phim/99/a-passage-to-india?tap=1
    // http://localhost:8080/xem-phim/90/the-sun-also-rises?tap=full
    @GetMapping("/xem-phim/{id}/{slug}")
    public String getXemPhimPage(Model model,
                                 @PathVariable Integer id,
                                 @PathVariable String slug,
                                 @RequestParam String tap) {
        Movie movie = movieService.getMovie(id, slug, true);
        List<Movie> relatedMovies = movieService.getRelatedMovies(id, movie.getType(), true, 6);
        List<Review> reviews = reviewService.getReviewsByMovie(id);
        List<Episode> episodes = episodeService.getEpisodeListOfMovie(id, true);
        Episode currentEpisode = episodeService.getEpisode(id, tap);

        model.addAttribute("movie", movie);
        model.addAttribute("relatedMovies", relatedMovies);
        model.addAttribute("reviews", reviews);
        model.addAttribute("episodes", episodes);
        model.addAttribute("currentEpisode", currentEpisode);
        return "html/xem-phim";
    }

    @GetMapping("/dang-nhap")
    public String getLoginPage() {
        return "html/login";
    }

    @GetMapping("/user/{id}/{email}")
    public String getUserPage(Model model, @PathVariable Integer id, @PathVariable String email) {
        User users = userService.getUserByNameAndEmail(id, email);
        model.addAttribute("users", users);
        return "html/user";
    }

    @GetMapping("/user/{id}/{email}/favorite")
    public String getFavoriteMovies(Model model, @PathVariable Integer id, @PathVariable String email) {
        User user = userService.getUserByNameAndEmail(id, email);

        if (user == null){
            return "html/index";
        }

        List<Movie> favoriteMovies = userService.getFavoritesById(user.getId());

        model.addAttribute("user", user);
        model.addAttribute("favoriteMovies", favoriteMovies);

        return "/html/favorite";
    }
}
