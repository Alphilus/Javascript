package com.example.demo.service;


import com.example.demo.entity.*;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.enums.MovieType;
import com.example.demo.model.request.CreateMovieRequest;
import com.example.demo.repository.*;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private EpisodeRepository episodeRepository;

    private final HttpSession session;
    private final CountryRepository countryRepository;
    private final DirectorRepository directorRepository;
    private final ActorRepository actorRepository;
    private final GenreRepository genreRepository;
    public List<Movie> getAllMovies() {
        return movieRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
    }

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

    public Movie createMovie(CreateMovieRequest request){
        User user = (User) session.getAttribute("currentUser");

        Country country = countryRepository.findById(request.getCountryId())
                .orElseThrow(() -> new ResourceNotFoundException("Country not found"));

        // Fetch Genre entities
        List<Genre> genres = genreRepository.findAllById(request.getGenreIds());
        if (genres.size() != request.getGenreIds().size()) {
            throw new ResourceNotFoundException("One or more genres not found");
        }

        // Fetch Actor entities
        List<Actor> actors = actorRepository.findAllById(request.getActorIds());
        if (actors.size() != request.getActorIds().size()) {
            throw new ResourceNotFoundException("One or more actors not found");
        }

        // Fetch Director entities
        List<Director> directors = directorRepository.findAllById(request.getDirectorIds());
        if (directors.size() != request.getDirectorIds().size()) {
            throw new ResourceNotFoundException("One or more directors not found");
        }

        Movie movie = Movie.builder()
                .name(request.getName())
                .description(request.getDescription())
                .releaseYear(request.getReleaseDate())
                .type(request.getType())
                .status(request.getStatus())
                .trailer(request.getTrailer())
                .country(country)
                .genres(genres)
                .actors(actors)
                .directors(directors)
                .build();

        return movieRepository.save(movie);
    }
}
