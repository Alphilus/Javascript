package com.example.demo.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Review {
    int id;
    String authorName;
    String authorAvatar;
    String content;
    int rating;
    LocalDateTime createdAt;
}
