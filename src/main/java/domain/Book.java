package domain;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Book extends Product {
    private String isbn;
    public Book() {}
    public Book(String isbn){
        super();
        this.isbn = isbn;
    }

    @Override
    public String toString() {
        return super.toString() +
                "\n\tisbn='" + isbn + '\'' +
                '}';
    }
}
