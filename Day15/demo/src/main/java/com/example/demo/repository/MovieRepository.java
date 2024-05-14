package com.example.demo.repository;

import com.example.demo.entity.Movie;
import com.example.demo.enums.MovieType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
    List<Movie> findByName(String name);
    List<Movie> findMovieByStatus(boolean status);
    List<Movie> findByNameIgnoreCase(String name);
    List<Movie> findByNameContaining(String keyword);
    List<Movie> findByNameAndSlug(String name, String slug);
    List<Movie> findByRatingBetween(double min, double max);
    List<Movie> findByRatingLessThanEqual(double max);
    List<Movie> findByCreatedAtAfter(LocalDateTime createdAt);
    List<Movie> findByTypeOrderByRatingDesc(MovieType type);
    Movie findFirstByTypeOrderByRating(MovieType type);
    Movie findMovieByIdAndSlug(Integer id, String slug);
    long countByStatus(boolean status);
    boolean existsByName(String name);
    Page<Movie> findByStatus(boolean status, Pageable pageable);
    List<Movie> findByTypeAndStatusOrderByCreatedAtDesc(MovieType type, Boolean status);
    Page<Movie> findByTypeAndStatus(MovieType type, Boolean status,Pageable pageable);
    List<Movie> findByType(MovieType type, Sort sort);

    @Query("SELECT DISTINCT m FROM Movie m JOIN m.genres g WHERE g.name IN :genres AND m.id <> :id")
    List<Movie> findRecommendedMovies(@Param("genres") List<String> genres, @Param("id") Integer id, Sort sort);
}
