package com.example.demo.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product {
    int id;
    String name;
    String description;
    String thumbnail;
    int price;
    double rating;
    int priceDiscount;
    List<Review> reviews = new ArrayList<Review>();
}
