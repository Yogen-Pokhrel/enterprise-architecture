package com.ea.lab4part1.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String isbn;
    private String title;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinTable(name = "author_books",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "publisher_id")
    )
    Publisher author;

    @Override
    public String toString() {
        return "Book{" + "\n" +
                "\t" + "id=" + id + ",\n" +
                "\t" + "isbn='" + isbn + "'," + "\n" +
                "\t" + "title='" + title + "'," + "\n" +
                "\t" + "author=" + author + "\n" +
                '}';
    }
}
