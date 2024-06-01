package com.example.demo.repository;

import com.example.demo.entity.Episode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EpisodeRepository extends JpaRepository<Episode, Integer> {
    List<Episode> findByMovie_IdOrderByDisplayOrder(Integer movieId);
    List<Episode> findByMovie_IdAndMovie_StatusOrderByDisplayOrderAsc(Integer movieId, Boolean status);

    Optional<Episode> findByMovie_IdAndMovie_StatusAndDisplayOrder(Integer movieId, Boolean status, Integer displayOrder);
}
