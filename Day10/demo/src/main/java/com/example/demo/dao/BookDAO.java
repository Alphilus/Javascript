package com.example.demo.dao;

import com.example.demo.model.Book;

import java.util.List;

public interface BookDAO {
    List<Book> findAll();

    List<Book> sortByYears();

    List<Book> findByTitle(String title);

    List<Book> findBetweenYears(int startYear, int endYear);
}
