package com.example.demo.db;

import com.example.demo.model.Product;
import com.example.demo.utils.IFileReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InitDB implements CommandLineRunner {
    @Autowired
    private IFileReader fileReader;
    @Override
    public void run(String... args) throws Exception {
        System.out.println("Khởi tạo dữ liệu");
        //Product
        ProductDB.productList = fileReader.readProducts("Product.json");
        for (Product product : ProductDB.productList){
            System.out.println("Product: " + product);
        }
        System.out.println("số lượng product: " + ProductDB.productList.size());

        //Review
        ReviewDB.reviewList = fileReader.readReviews("Review.json");
        ReviewDB.reviewList.forEach(System.out::println);
        System.out.println("Review: " + ReviewDB.reviewList.size());
    }
}
