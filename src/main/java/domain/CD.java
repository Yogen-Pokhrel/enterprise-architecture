package domain;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

@Entity
@Getter
@Setter
@NamedQueries({
        @NamedQuery(
                name = "CD.findCDsByArtist",
                query = "SELECT cd FROM CD cd WHERE cd.artist = :artist"
        )
})
public class CD extends Product {
    private String artist;
    public CD() {}
    public CD(String artist) {
        super();
        this.artist = artist;
    }

    @Override
    public String toString() {
        return super.toString() + "\n\t" +
                "artist='" + artist + '\'' +
                '}';
    }
}
