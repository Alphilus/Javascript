package com.example.demo.service;

import com.example.demo.entity.Movie;
import com.example.demo.entity.Review;
import com.example.demo.entity.User;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.request.UpsertReviewRequest;
import com.example.demo.repository.MovieRepository;
import com.example.demo.repository.ReviewRepository;
import com.example.demo.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final MovieRepository movieRepository;
    private final HttpSession session;

    public List<Review> getReviewsByMovie(Integer id) {
        return reviewRepository.findByMovie_IdOrderByCreatedAtDesc(id);
    }

        // TODO: Validate thong tin: content, rating, ... su dung thu vien Validation
        public Review createReview(UpsertReviewRequest request) {
            User user = (User) session.getAttribute("currentUser");

            // Kiem tra xem movie co ton tai hay khong?
            Movie movie = movieRepository.findById(request.getMovieId())
                    .orElseThrow(() -> new ResourceNotFoundException("Movie not found"));

            // Tao review
            Review review = Review.builder()
                    .content(request.getContent())
                    .rating(request.getRating())
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .movie(movie)
                    .user(user)
                    .build();

            reviewRepository.save(review);
            return review;
        }

    public Review updateReview(Integer id, UpsertReviewRequest request) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Review not found"));

        User user = (User) session.getAttribute("currentUser");

        // Kiem tra xem movie co ton tai hay khong?
        Movie movie = movieRepository.findById(request.getMovieId())
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found"));

        // Kiem tra xem review nay co phai cua user hay khong?
        if (!review.getUser().getId().equals(user.getId())) {
            throw new BadRequestException("Not review's owner");
        }

        // Kiem tra xem review nay co thuoc movie hay khong
        if (!review.getMovie().getId().equals(movie.getId())) {
            throw new BadRequestException("Not review's movie");
        }

        // Cap nhat review
        review.setContent(request.getContent());
        review.setRating(request.getRating());
        review.setUpdatedAt(LocalDateTime.now());

        reviewRepository.save(review);
        return review;
    }

    public void deleteReview(Integer id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Review not found"));

        User user = (User) session.getAttribute("currentUser");

        // Kiem tra xem review nay co phai cua user hay khong?
        if (!review.getUser().getId().equals(user.getId())) {
            throw new BadRequestException("Not review's owner");
        }

        reviewRepository.delete(review);
    }
}
