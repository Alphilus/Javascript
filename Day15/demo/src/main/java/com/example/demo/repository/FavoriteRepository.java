package com.example.demo.repository;

import com.example.demo.entity.Favorite;
import com.example.demo.entity.Movie;
import com.example.demo.entity.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FavoriteRepository extends JpaRepository<Favorite, Integer> {
    List<Favorite> findFavoriteById(Integer id);
    Optional<Favorite> findByUserAndMovie(User user, Movie movie);
}
