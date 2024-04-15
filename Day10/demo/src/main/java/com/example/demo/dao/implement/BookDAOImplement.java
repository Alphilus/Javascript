package com.example.demo.dao.implement;

import com.example.demo.dao.BookDAO;
import com.example.demo.database.BookDB;
import com.example.demo.model.Book;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class BookDAOImplement implements BookDAO {
    private final List<Book> books;

    public BookDAOImplement(){
        this.books = BookDB.books;
    }

    @Override
    public List<Book> findAll() {
        return BookDB.books;
    }

    @Override
    public List<Book> sortByYears() {
        return books.stream()
                .sorted(Comparator.comparingInt(Book::getYear).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public List<Book> findByTitle(String title) {
        return books.stream()
                .filter(book -> book.getTitle().toLowerCase().contains(title.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Book> findBetweenYears(int startYear, int endYear) {
        return books.stream()
                .filter(book -> book.getYear() >= startYear && book.getYear() <= endYear)
                .collect(Collectors.toList());
    }
}
