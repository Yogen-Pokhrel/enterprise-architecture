package com.lab9.book.dto.request;

import lombok.Data;

@Data
public class UpdateBookDto {
    //only the provided fields will be updated
    private String title;
    private String author;
    private Float price;
}
