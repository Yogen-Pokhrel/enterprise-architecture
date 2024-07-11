package books.web;


import java.util.*;
import java.util.stream.Collectors;

import books.domain.Book;
import books.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
public class BookServiceController {

    @Autowired
    BookService bookService;

	@GetMapping("/books/{isbn}")
    public ResponseEntity<?> getBook(@PathVariable String isbn) {
        Book book = bookService.findByIsbn(isbn);
        if (book == null) {
            return new ResponseEntity<CustomErrorType>(new CustomErrorType("Book with isbn= " + isbn + " is not available"),HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Book> (book, HttpStatus.OK);
    }

	@DeleteMapping("/books/{isbn}")
    public ResponseEntity<?> deleteBook(@PathVariable String isbn) {
        Book book = bookService.findByIsbn(isbn);
        if (book == null) {
            return new ResponseEntity<CustomErrorType>(new CustomErrorType("Book with isbn = " + isbn + " is not available"),HttpStatus.NOT_FOUND);
        }
        bookService.delete(isbn);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
	
	@PostMapping("/books")
    public ResponseEntity<?> addContact(@Valid @RequestBody Book book) {
        bookService.add(book);
        return new ResponseEntity<Book> (book, HttpStatus.OK);
    }

	@PutMapping("/books/{isbn}")
    public ResponseEntity<?> updateBook(@PathVariable String isbn, @Valid @RequestBody Book book) {
        bookService.update(book);
		return new ResponseEntity<Book> (book, HttpStatus.OK);
    }

    @GetMapping("/books")
    public ResponseEntity<?> searchBooks(@RequestParam(value="author", required = false) String author) {
        Books allbooks = new Books();
        if (author == null){  //get all books
            allbooks.setBooks(bookService.findAll());
        }
        else{ //get books from an certain author
            String authorName = author.substring(1,author.length()-1); //remove quotes form the name
            List<Book> booklist= bookService.findByAuthor(authorName);
            allbooks.setBooks(booklist);
        }
        return new ResponseEntity<Books> (allbooks, HttpStatus.OK);
    }
}


