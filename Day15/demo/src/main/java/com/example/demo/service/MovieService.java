package com.example.demo.service;

import com.example.demo.entity.Movie;
import com.example.demo.enums.MovieType;
import com.example.demo.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    public Movie getMovieById(Integer id, String slug) {
        return movieRepository.findMovieByIdAndSlug(id, slug);
    }

    public List<Movie> layDanhSachPhimBo() {
        return movieRepository.findByTypeAndStatusOrderByCreatedAtDesc(MovieType.valueOf("PHIM_BO"), true);
    }

    public List<Movie> layDanhSachPhimLe() {
        return movieRepository.findByTypeAndStatusOrderByCreatedAtDesc(MovieType.valueOf("PHIM_LE"), true);
    }

    public List<Movie> layDanhSachPhimChieuRap() {
        return movieRepository.findByTypeAndStatusOrderByCreatedAtDesc(MovieType.valueOf("PHIM_CHIEU_RAP"), true);
    }

    public Page<Movie> getMovieByType(MovieType movieType, Boolean status, int page, int pageSize) {
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by("createdAt").descending());
        return movieRepository.findByTypeAndStatus(movieType, status, pageRequest);
    }
}
