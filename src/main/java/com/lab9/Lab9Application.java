package com.lab9;

import com.lab9.book.dto.request.BookFilterDto;
import com.lab9.book.dto.request.CreateBookDto;
import com.lab9.book.dto.request.UpdateBookDto;
import com.lab9.book.dto.response.BookDto;
import com.lab9.book.entity.Book;
import com.lab9.common.ApiResponse;
import com.lab9.common.exception.ApiException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

@SpringBootApplication
public class Lab9Application implements CommandLineRunner {
	@Autowired
	Client client;

	public static void main(String[] args) {
		SpringApplication.run(Lab9Application.class, args);
	}

	@Bean
	ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {
		try {
			// Create a book
			String isbn = "1234" + Math.random();
			CreateBookDto createBookDto = new CreateBookDto();
			createBookDto.setTitle("Effective Java");
			createBookDto.setAuthor("Joshua Bloch");
			createBookDto.setIsbn(isbn);
			createBookDto.setPrice(45.0f);
			BookDto createdBook = client.addBook(createBookDto);
			System.out.println("\n\nCreated Book: " + createdBook);

			try{
				//this should throw an error in second request
				createdBook = client.addBook(createBookDto);
				System.out.println("\n\nCreated Book: " + createdBook);
			}catch (ApiException e){
				System.out.println("\n\nError: " + e.getMessage());
				System.out.println("Success: " + e.isSuccess());
			}

			// Update the book
			UpdateBookDto updateBookDto = new UpdateBookDto();
			updateBookDto.setTitle("Effective Java 3rd Edition");
			updateBookDto.setPrice(55.0f);
			BookDto updatedBook = client.updateBook(isbn, updateBookDto);
			System.out.println("\n\nUpdated Book: " + updatedBook);

			// Get the book
			BookDto retrievedBook = client.getBook(isbn);
			System.out.println("\n\nRetrieved Book: " + retrievedBook);

			// Get all books
			BookFilterDto bookFilterDto = new BookFilterDto();
			bookFilterDto.setAuthor("Joshua Bloch");
			List<BookDto> books = client.getAllBooks(bookFilterDto);
			System.out.println("\n\nAll Books: ");
			for (BookDto bookDto : books) {
				System.out.println(bookDto);
			}

			// Delete the book
			client.deleteBook(isbn);
			System.out.println("\n\nBook deleted successfully");

		} catch (IOException e) {
			System.out.println("\n\nError: "+ e.getMessage());
			e.printStackTrace();
		}
	}
}
