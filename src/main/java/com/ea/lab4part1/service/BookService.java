package com.ea.lab4part1.service;

import com.ea.lab4part1.domain.Book;
import com.ea.lab4part1.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    public List<Book> findAll(){
        return bookRepository.findAll();
    }

    public Book findById(Long id){
        return bookRepository.findById(id).get();
    }

    public Book save(Book book){
        return bookRepository.save(book);
    }
}
