package books.service;

import books.data.BookRepository;
import books.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;


    public void add(Book book){
        bookRepository.save(book);
    }

    public void update(Book book){
        bookRepository.save(book);
    }

    public Book findByIsbn(String isbn){
        return bookRepository.findByIsbn(isbn);
    }

    public List<Book> findByAuthor(String author){
        return bookRepository.findByAuthor(author);
    }

    public void delete(String isbn){
        bookRepository.delete(isbn);
    }

    public Collection<Book> findAll(){
        return bookRepository.findAll();
    }
}
