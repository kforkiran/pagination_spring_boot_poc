package com.example.pagination.controller;

import com.example.pagination.model.BookPageResponse;
import com.example.pagination.service.BookService;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
public class BookController {

    private final BookService booksService;

    public BookController(BookService booksService) {
        this.booksService = booksService;
    }

    @GetMapping("/books")
    public BookPageResponse getBooks(Pageable pageable) {
        return booksService.getBooks(pageable);
    }
}
