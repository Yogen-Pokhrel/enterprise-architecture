package com.lab9.book;

import com.lab9.book.dto.request.BookFilterDto;
import com.lab9.book.dto.request.CreateBookDto;
import com.lab9.book.dto.request.UpdateBookDto;
import com.lab9.book.dto.response.BookDto;
import com.lab9.common.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    BookService bookService;

    @PostMapping
    public ResponseEntity<ApiResponse<BookDto>> addBook(@Valid @RequestBody CreateBookDto createBookDto) throws Exception {
        BookDto bookDto = bookService.save(createBookDto);
        return ResponseEntity.ok(ApiResponse.success(bookDto, "Book Created Successfully"));
        //In case of error it is caught by Global Exception handler and respective error with status code is sent to client
    }

    @PutMapping("/{isbn}")
    public ResponseEntity<ApiResponse<BookDto>> updateBook(@PathVariable String isbn, @RequestBody UpdateBookDto updateBookDto) throws Exception{
        BookDto bookDto = bookService.update(isbn, updateBookDto);
        return ResponseEntity.ok(ApiResponse.success(bookDto, "Book Updated Successfully"));
    }

    @DeleteMapping("/{isbn}")
    public ResponseEntity<ApiResponse<?>> deleteBook(@PathVariable String isbn) throws Exception {
        bookService.delete(isbn);
        return ResponseEntity.ok(ApiResponse.success(null, "Book Deleted Successfully"));
    }

    @GetMapping("/{isbn}")
    public ResponseEntity<ApiResponse<BookDto>> getBook(@PathVariable String isbn) throws Exception {
        BookDto bookDto= bookService.findById(isbn);
        return ResponseEntity.ok(ApiResponse.success(bookDto, "Book Retrieved Successfully"));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<BookDto>>> getAllBooks(BookFilterDto bookFilterDto){
        List<BookDto> books = bookService.findAll(bookFilterDto);
        return ResponseEntity.ok(ApiResponse.success(books, "Books Retrieved Successfully"));
    }
}
