package com.example.demo.service;

import com.example.demo.model.Product;
import com.example.demo.model.Review;
import com.example.demo.response.PageResponse;

import java.util.List;

public interface ProductService {
    List<Product> getProducts();
    Product getById(int id);
    List<Review> getReview();
    List<Product> getProductsByKeywords(String keywords);
    List<Product> getSortedProductsByRating();
    List<Product> getSortedProductsByRatingReverse();
    PageResponse<Product> getProduct(int page, int pageSize);
}
