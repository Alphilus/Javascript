package com.example.demo.entity;

import com.example.demo.enums.MovieType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String name;
    String slug;

    @Column(columnDefinition = "TEXT")
    String description;

    String poster;
    Integer releaseYear;
    Double rating;

    @Enumerated(EnumType.STRING)
    MovieType type;

    Boolean status;
    String trailer;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}