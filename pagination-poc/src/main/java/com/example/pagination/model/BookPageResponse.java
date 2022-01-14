package com.example.pagination.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookPageResponse {
    private List<BookDto> bookDtos;
    private int totalRecords;
}
