package com.example.demo.dao.impl;

import com.example.demo.dao.ProductDAO;
import com.example.demo.db.ProductDB;
import com.example.demo.db.ReviewDB;
import com.example.demo.model.Product;
import com.example.demo.model.Review;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.List;

@Repository
public class ProductDAOImpl implements ProductDAO {
    @Override
    public void printProduct(List<Product> products) {
        products.forEach(System.out::println);
    }

    @Override
    public List<Product> findAllProducts() {
        return ProductDB.productList;
    }

    @Override
    public Product findById(int id) {
        return ProductDB.productList.stream()
                .filter(product -> product.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Review> findReview() {
        return  ReviewDB.reviewList;
    }

    @Override
    public List<Product> findProductsByKeywords(String keywords) {
        return ProductDB.productList.stream()
                .filter(products -> products.getName().toLowerCase().contains(keywords))
                .toList();
    }

    @Override
    public List<Product> sortProductsByRating() {
        return ProductDB.productList.stream()
                .sorted(Comparator.comparing(Product::getRating))
                .toList();
    }

    @Override
    public List<Product> sortProductsByRatingReverse() {
        return ProductDB.productList.stream()
                .sorted(Comparator.comparing(Product::getRating).reversed())
                .toList();
    }
}
