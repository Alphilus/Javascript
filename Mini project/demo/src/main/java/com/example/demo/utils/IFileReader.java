package com.example.demo.utils;

import com.example.demo.model.Product;
import com.example.demo.model.Review;

import java.util.List;

public interface IFileReader {
    List<Product> readProducts(String path);
    List<Review> readReviews(String path);
}
