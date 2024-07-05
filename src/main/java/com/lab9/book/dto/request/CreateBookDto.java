package com.lab9.book.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateBookDto {
    @NotBlank(message = "Book title name is mandatory")
    @Size(min = 1, max = 50, message = "Book title must be between 1 and 120 characters")
    private String title;

    @NotBlank(message = "Author name is mandatory")
    @Size(min = 1, max = 50, message = "Author name should be greater than 1 character")
    private String author;

    @NotBlank(message = "ISBN is mandatory")
    @Size(min = 1, max = 50, message = "ISBN should be greater than 1 number/letter")
    private String isbn;

    @Positive(message = "Price should be a positive number")
    private Float price;
}
