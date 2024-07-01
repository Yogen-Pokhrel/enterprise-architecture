package repositories;

import domain.CD;
import domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT cd FROM CD cd WHERE cd.artist = :artist AND cd.price > :amount")
    List<CD> findCDsByArtistAndPriceGreaterThan(@Param("artist") String artist, @Param("amount") double amount);

    @Query(name = "CD.findCDsByArtist")
    List<CD> findCDsByArtist(@Param("artist") String artist);
}
