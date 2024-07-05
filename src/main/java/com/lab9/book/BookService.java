package com.lab9.book;

import com.lab9.book.dto.request.BookFilterDto;
import com.lab9.book.dto.request.CreateBookDto;
import com.lab9.book.dto.request.UpdateBookDto;
import com.lab9.book.dto.response.BookDto;
import com.lab9.book.entity.Book;
import com.lab9.book.repository.BookRepository;
import com.lab9.book.repository.BookSpecification;
import com.lab9.common.exception.DuplicateResourceException;
import jakarta.persistence.NoResultException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    ModelMapper modelMapper;

    public List<BookDto> findAll(BookFilterDto bookFilterDto) {
        List<Book> books = bookRepository.findAll(BookSpecification.filterBy(bookFilterDto));
        List<BookDto> bookDtoList = new ArrayList<>();
        for (Book book : books) {
            bookDtoList.add(modelMapper.map(book, BookDto.class));
        }
        return bookDtoList;
    }

    public BookDto save(CreateBookDto createBookDto) throws Exception {
        Book book  = bookRepository.findById(createBookDto.getIsbn()).orElse(null);
        if (book != null) {
            throw new DuplicateResourceException("This book already exists in the system");
        }
        book = bookRepository.save(modelMapper.map(createBookDto, Book.class));
        return modelMapper.map(book, BookDto.class);
    }

    public BookDto findById(String id) throws Exception {
        Book book = bookRepository.findById(id).orElseThrow(() -> new NoResultException("Book not found"));
        return modelMapper.map(book, BookDto.class);
    }

    public void delete(String id) throws Exception {
        Book book = bookRepository.findById(id).orElseThrow(() -> new NoResultException("Book not found"));
        bookRepository.delete(book);
    }

    public BookDto update(String id, UpdateBookDto updateBookDto) throws Exception {
        Book book = bookRepository.findById(id).orElseThrow(() -> new NoResultException("Book not found"));
        if (updateBookDto.getTitle() != null) {
            book.setTitle(updateBookDto.getTitle());
        }
        if (updateBookDto.getAuthor() != null) {
            book.setAuthor(updateBookDto.getAuthor());
        }
        if(updateBookDto.getPrice() != null){
            book.setPrice(updateBookDto.getPrice());
        }
        bookRepository.save(book);
        return modelMapper.map(book, BookDto.class);
    }
}
