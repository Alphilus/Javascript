package com.example.demo.model;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.logging.Level;

@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)

public class Book {
    private int id;
    private String title;
    private String author;
    private int year;
}
