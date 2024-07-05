package com.lab9.book.dto.response;

import lombok.Data;

@Data
public class BookDto {
    private String title;
    private String author;
    private String isbn;
    private Float price;
}
