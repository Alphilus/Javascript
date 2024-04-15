package com.example.demo.service;

import com.example.demo.model.Book;

import java.util.List;

public interface BookService {
    List<Book> getAllBooks();

    List<Book> sortBooksByYears();

    List<Book> getBooksByTitle(String title);

    List<Book> getBooksBetweenYears(int startYear, int endYear);
}
