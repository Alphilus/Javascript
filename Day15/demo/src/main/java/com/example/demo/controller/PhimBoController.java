package com.example.demo.controller;

import com.example.demo.entity.Movie;
import com.example.demo.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class PhimBoController {
    @Autowired
    private MovieService movieService;
    @GetMapping("/phim-bo")
    public String phimBo(Model model) {
        List<Movie> danhSachPhimBo = movieService.layDanhSachPhimBo();
        model.addAttribute("danhSachPhimBo", danhSachPhimBo);
        return "phim-bo";
    }

    @GetMapping("/phim-le")
    public String phimLe(Model model) {
        List<Movie> danhSachPhimLe = movieService.layDanhSachPhimLe();
        model.addAttribute("danhSachPhimLe", danhSachPhimLe);
        return "phim-le";
    }

    @GetMapping("/phim-chieu-rap")
    public String phimChieuRap(Model model) {
        List<Movie> danhSachPhimChieuRap = movieService.layDanhSachPhimChieuRap();
        model.addAttribute("danhSachPhimChieuRap", danhSachPhimChieuRap);
        return "phim-chieu-rap";
    }
}
