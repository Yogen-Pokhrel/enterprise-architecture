package domain;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class DVD extends Product{
    private String genre;
    public DVD() {}
    public DVD(String genre) {
        super();
        this.genre = genre;
    }
}
