package domain;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CD extends Product {
    private String artist;
    public CD() {}
    public CD(String artist) {
        super();
        this.artist = artist;
    }
}
