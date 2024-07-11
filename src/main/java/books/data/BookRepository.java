package books.data;

import books.domain.Book;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class BookRepository  {

    private Map<String, Book> books = new HashMap<String, Book>();

    public void save(Book book){
        books.put(book.getIsbn(),book);
    }

    public Book findByIsbn(String isbn){
        return books.get(isbn);
    }

    public void delete(String isbn){
        books.remove(isbn);
    }

    public Collection<Book> findAll(){
        return books.values();
    }

    public List<Book> findByAuthor(String authorName) {
        return books.values().stream().filter(b->b.getAuthor().equals(authorName)).collect(Collectors.toList());
    }
}
