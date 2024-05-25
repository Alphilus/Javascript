package com.example.demo.service;


import com.example.demo.entity.Episode;
import com.example.demo.entity.Movie;
import com.example.demo.model.enums.MovieType;
import com.example.demo.repository.EpisodeRepository;
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

    @Autowired
    private EpisodeRepository episodeRepository;

    public Movie getMovieById(Integer id, String slug) {
        return movieRepository.findMovieByIdAndSlug(id, slug);
    }

    public List<Movie> getHotMovies(boolean status) {
        return movieRepository.findMovieByStatus(status);
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

    public List<Movie> getRecommendedMovie(List<String> genre, Integer id){
        Sort sort = Sort.by(Sort.Order.desc("rating"));
        return movieRepository.findRecommendedMovies(genre, id, sort);
    }

    public List<Episode> getEpisodeByMovieId(Integer id) {
        return episodeRepository.findByMovie_IdOrderByDisplayOrder(id);
    }
    public Page<Movie> GetMovieByStatus(Boolean status, int page, int pageSize){
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by("createdAt").descending());
        return movieRepository.findByStatus(status, pageRequest);
    }
}
