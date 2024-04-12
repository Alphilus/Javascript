package com.example.demo.controller;

import com.example.demo.model.Book;
import com.example.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("books")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("/home")
    public String getHome(){
        return "index";
    }

    @GetMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public List<Book> getAllBook(){
        return bookService.getAllBooks();
    }

    @GetMapping("getAllBooks")
    public ResponseEntity<List<Book>> getAllBooks1(){
        return new ResponseEntity<>(bookService.getAllBooks(), HttpStatus.CREATED);
    }

    @GetMapping("/sortByYear")
    @ResponseBody
    public List<Book> sortBooksByYear(){
        return bookService.sortBooksByYears();
    }

    @GetMapping("/search/{title}")
    @ResponseBody
    public  Book findBooksByKeyword(@PathVariable String title){
        return bookService.getBooksByTitle(title);
    }

    @GetMapping("/startYear/{startYear}/endYear/{endYear}")
    @ResponseBody
    public List<Book> getBooksBetweenYears(@PathVariable int startYear, @PathVariable int endYear){
        return bookService.getBooksBetweenYears(startYear, endYear);
    }
}
