package com.example.demo.controller;

import com.example.demo.entity.Blog;
import com.example.demo.entity.Movie;
import com.example.demo.enums.MovieType;
import com.example.demo.service.BlogService;
import com.example.demo.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class WebController {
    @Autowired
    private MovieService movieService;

    @Autowired
    private BlogService blogService;

    @GetMapping("/")
    public String getHomePage(Model model) {
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
        model.addAttribute("blog", blog);
        return "/html/tin-tuc-chi-tiet";
    }
    @GetMapping("/phim/{id}/{slug}")
    public String phimChiTiet(Model model, @PathVariable String slug, @PathVariable Integer id) {
        Movie movie = movieService.getMovieById(id, slug);
        model.addAttribute("movie", movie);
        return "/html/detail";
    }
}
