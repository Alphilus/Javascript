package com.example.demo.dao.implement;

import com.example.demo.dao.BookDAO;
import com.example.demo.database.BookDB;
import com.example.demo.model.Book;
import com.example.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

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
        BookDB.books.sort(Comparator.comparingInt(Book::getYear));
        return BookDB.books;
    }

    @Override
    public Book findByTitle(String title) {
        for (Book book : BookDB.books){
            if (Objects.equals(book.getTitle(), title)){
                return book;
            }
        }
        return null;
    }

    @Override
    public List<Book> findBetweenYears(int startYear, int endYear) {
        List<Book> booksBetweenYears = new ArrayList<>();

        for (Book book : BookDB.books){
            if (book.getYear() >= startYear && book.getYear() <= endYear){
                booksBetweenYears.add(book);
            }
        }

        return booksBetweenYears.isEmpty() ? null : booksBetweenYears;
    }
}
