package repositories;

import domain.CD;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CDRepository extends JpaRepository<CD, Integer> , JpaSpecificationExecutor<CD> {
    List<CD> findByArtistAndPriceLessThan(String artist, double price);

    @Query(value = "SELECT p.*, c.* FROM Product p JOIN CD c ON p.id = c.id WHERE c.artist = :artist", nativeQuery = true)
    List<CD> findAllCDsByArtistU2(@Param("artist") String artist);
}
