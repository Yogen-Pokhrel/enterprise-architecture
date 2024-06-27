package com.ea.lab4part1.repository;

import com.ea.lab4part1.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
