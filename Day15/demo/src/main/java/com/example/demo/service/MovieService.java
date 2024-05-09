package com.example.demo.service;

import com.example.demo.entity.Movie;
import com.example.demo.enums.MovieType;
import com.example.demo.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    public List<Movie> layDanhSachPhimBo() {
        return movieRepository.findByTypeAndStatusOrderByCreatedAtDesc(MovieType.valueOf("PHIM_BO"), true);
    }

    public List<Movie> layDanhSachPhimLe() {
        return movieRepository.findByTypeAndStatusOrderByCreatedAtDesc(MovieType.valueOf("PHIM_LE"), true);
    }

    public List<Movie> layDanhSachPhimChieuRap() {
        return movieRepository.findByTypeAndStatusOrderByCreatedAtDesc(MovieType.valueOf("PHIM_CHIEU_RAP"), true);
    }
}
