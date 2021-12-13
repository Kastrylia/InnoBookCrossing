package com.example.innobookcrossing.controller;

import com.example.innobookcrossing.jpa.BookRepository;
import com.example.innobookcrossing.model.Book;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class MainPageController {

    @Autowired
    private BookRepository bookRepository;

    @ApiOperation("Returns all Books. MAIN PAGE / LIBRARY")
    @GetMapping("book/get/all")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    @ApiOperation("Get operation. Return book by specified ID. MAIN PAGE / LIBRARY")
    @GetMapping("book/get/{id}")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public Map<String, String> getBook(@PathVariable Integer id) {
        Book book = bookRepository.findBookById(id);
        HashMap<String, String> map = new HashMap<>();
        map.put("title", book.getTitle());
        map.put("author", book.getAuthor());
        map.put("genre", book.getGenre());
        map.put("description", book.getDescription());
        map.put("image", book.getImage());
        map.put("available", book.getAvailable().toString());
        map.put("alias", book.getUser().getAlias());
        return map;
    }

    @ApiOperation("Search for a book. MAIN PAGE / LIBRARY")
    @GetMapping("book/search")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public Book getBooks(@RequestParam
                         @Parameter(description = "Searching book by title") String search) {
        return bookRepository.findBookByTitle(search);
    }
}
