package com.example.pagination.service;

import com.example.pagination.dao.BookRepository;
import com.example.pagination.entity.BookEntity;
import com.example.pagination.model.BookDto;
import com.example.pagination.model.BookPageResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public BookPageResponse getBooks(Pageable pageable) {
        Page<BookEntity> bookEntityPage = bookRepository.findAll(pageable);
        List<BookDto> bookDtoList = bookEntityPage.getContent().stream()
                .map(this::toBookDto)
                .collect(Collectors.toList());
        return BookPageResponse.builder().bookDtos(bookDtoList).build();
    }

    private BookDto toBookDto(BookEntity bookEntity) {
        return BookDto.builder().id(bookEntity.getId()).author(bookEntity.getAuthor()).name(bookEntity.getName()).build();
    }
}
