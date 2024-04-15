package com.example.demo.service.implement;

import com.example.demo.dao.BookDAO;
import com.example.demo.model.Book;
import com.example.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BookServiceImplement implements BookService {
    private final BookDAO bookDAO;
    @Autowired
    private BookServiceImplement(BookDAO bookDAO){
        this.bookDAO = bookDAO;
    }

    @Override
    public List<Book> getAllBooks() {
        return bookDAO.findAll();
    }

    @Override
    public List<Book> sortBooksByYears() {
        return bookDAO.sortByYears();
    }

    @Override
    public List<Book> getBooksByTitle(String title) {
        return bookDAO.findByTitle(title);
    }

    @Override
    public List<Book> getBooksBetweenYears(int startYear, int endYear) {
        return bookDAO.findBetweenYears(startYear, endYear);
    }
}
