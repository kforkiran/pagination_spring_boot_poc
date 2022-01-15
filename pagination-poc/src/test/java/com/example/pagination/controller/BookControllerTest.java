package com.example.pagination.controller;

import com.example.pagination.model.BookDto;
import com.example.pagination.model.BookPageResponse;
import com.example.pagination.service.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = BookController.class)
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Test
    void getBooks() throws Exception {
        List<BookDto> bookDtos = getBookDtos();
        BookPageResponse bookPageResponse = BookPageResponse.builder()
                .bookDtos(bookDtos)
                .totalRecords(2)
                .build();
        given(bookService.getBooks(Mockito.any(Pageable.class))).willReturn(bookPageResponse);
        mockMvc.perform(get("/books")
                        .accept(MediaType.APPLICATION_JSON)
                        .queryParam("sort", "name,desc"))
                .andExpect(jsonPath("totalRecords").value(2));
    }

    private List<BookDto> getBookDtos() {
        BookDto book1 = BookDto.builder()
                .id(1L)
                .name("Java")
                .author("kiran")
                .build();

        BookDto book2 = BookDto.builder()
                .id(2L)
                .name("Clojure")
                .author("pawar")
                .build();

        return List.of(book1, book2);
    }
}