package com.example.demo.repository;

import com.example.demo.entity.Episode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EpisodeRepository extends JpaRepository<Episode, Integer> {
    List<Episode> findByMovie_IdOrderByDisplayOrder(Integer movieId);
}
