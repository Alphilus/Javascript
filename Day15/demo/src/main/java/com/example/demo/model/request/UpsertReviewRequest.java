package com.example.demo.model.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpsertReviewRequest {
    String content;
    Integer rating;
    Integer movieId;
}
