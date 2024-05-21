package com.example.demo.service;

import com.example.demo.entity.Favorite;
import com.example.demo.entity.Movie;
import com.example.demo.entity.User;
import com.example.demo.model.request.UpdatePasswordRequest;
import com.example.demo.model.request.UpdateProfileUserRequest;
import com.example.demo.repository.FavoriteRepository;
import com.example.demo.repository.MovieRepository;
import com.example.demo.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    @Autowired
    private FavoriteRepository favoriteRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MovieRepository movieRepository;

    private final HttpSession session;

    private final BCryptPasswordEncoder passwordEncoder;

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User getUserByNameAndEmail(Integer id, String email) {
        return userRepository.findUserByIdAndEmail(id, email);
    }

    public Integer getCurrentUserId() {
        User user = (User) session.getAttribute("currentUser");
        return user != null ? user.getId() : null;
    }

    public User updateUserProfile(Integer id, String name) {
        User user = (User) session.getAttribute("currentUser");
        if (user != null){
            user.setName(name);
            return userRepository.save(user);
        } else {
            throw new IllegalArgumentException("User not found with ID: " + id);
        }
    }

    public void updateUserPassword(Integer id, UpdatePasswordRequest request) {
        User user = (User) session.getAttribute("currentUser");
        if (user == null || !user.getId().equals(id)) {
            throw new IllegalArgumentException("User not found or ID mismatch");
        }

        if (!passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Old password is incorrect");
        }

        if (!request.getNewPassword().equals(request.getConfirmPassword())) {
            throw new IllegalArgumentException("New passwords do not match");
        }

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);
    }

    public List<Movie> getFavoritesById(Integer id) {
        return favoriteRepository.findFavoriteById(id)
                .stream()
                .map(Favorite::getMovie)
                .toList();
    }

    public Movie addToFavorite(Integer userId, Integer movieId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new RuntimeException("Movie not found"));

        Favorite favorite = Favorite.builder()
                .user(user)
                .movie(movie)
                .build();

        return favoriteRepository.save(favorite).getMovie();
    }

    public void removeFromFavorites(Integer userId, Integer movieId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new RuntimeException("Movie not found"));

        Favorite favorite = favoriteRepository.findByUserAndMovie(user, movie)
                .orElseThrow(() -> new RuntimeException("Favorite not found"));

        favoriteRepository.delete(favorite);
    }
}
