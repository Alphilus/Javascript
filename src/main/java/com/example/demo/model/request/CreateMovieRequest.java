package com.example.demo.model.request;

import com.example.demo.model.enums.MovieType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateMovieRequest {
    String name;
    String description;
    Integer releaseDate;
    MovieType type;
    Boolean status;
    String trailer;
    Integer countryId;
    List<Integer> genreIds;
    List<Integer> actorIds;
    List<Integer> directorIds;
}
