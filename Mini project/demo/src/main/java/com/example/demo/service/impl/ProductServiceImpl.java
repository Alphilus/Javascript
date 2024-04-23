package com.example.demo.service.impl;

import com.example.demo.dao.ProductDAO;
import com.example.demo.model.Product;
import com.example.demo.model.Review;
import com.example.demo.response.PageResponse;
import com.example.demo.response.PageResponseImpl;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDAO productDAO;
    @Override
    public List<Product> getProducts() {
        return productDAO.findAllProducts();
    }

    @Override
    public Product getById(int id) {
        return productDAO.findById(id);
    }

    @Override
    public List<Review> getReview() {
        return productDAO.findReview();
    }

    @Override
    public List<Product> getProductsByKeywords(String keywords) {
        return productDAO.findProductsByKeywords(keywords);
    }

    @Override
    public List<Product> getSortedProductsByRating() {
        return productDAO.sortProductsByRating();
    }

    @Override
    public List<Product> getSortedProductsByRatingReverse() {
        return productDAO.sortProductsByRatingReverse();
    }

    @Override
    public PageResponse<Product> getProduct(int page, int pageSize) {
        return new PageResponseImpl<>(productDAO.findAllProducts(), page, pageSize);
    }
}
