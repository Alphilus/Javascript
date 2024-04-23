package com.example.demo.dao;

import com.example.demo.model.Product;
import com.example.demo.model.Review;

import java.util.List;

public interface ProductDAO {
    void printProduct(List<Product> productList);

    List<Product> findAllProducts();

    Product findById(int id);

    List<Review> findReview();

    List<Product> findProductsByKeywords(String keywords);

    List<Product> sortProductsByRating();

    List<Product> sortProductsByRatingReverse();

}
