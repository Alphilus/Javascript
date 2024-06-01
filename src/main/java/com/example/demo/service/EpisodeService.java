package com.example.demo.service;

import com.example.demo.entity.Episode;
import com.example.demo.repository.EpisodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EpisodeService {
    private final EpisodeRepository episodeRepository;

    public List<Episode> getEpisodeListOfMovie(Integer movieId) {
        return episodeRepository.findByMovie_IdOrderByDisplayOrder(movieId);
    }

    public List<Episode> getEpisodeListOfMovie(Integer movieId, Boolean status) {
        return episodeRepository.findByMovie_IdAndMovie_StatusOrderByDisplayOrderAsc(movieId, status);
    }

    public Episode getEpisode(Integer movieId, String tap) {
        if (tap.equals("full")) {
            return episodeRepository
                    .findByMovie_IdAndMovie_StatusAndDisplayOrder(movieId, true, 1)
                    .orElse(null);
        } else {
            return episodeRepository
                    .findByMovie_IdAndMovie_StatusAndDisplayOrder(movieId, true, Integer.parseInt(tap))
                    .orElse(null);
        }
    }
}