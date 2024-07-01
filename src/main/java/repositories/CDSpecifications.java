package repositories;

import domain.CD;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class CDSpecifications {
    public static Specification<CD> byArtistAndPriceGreaterThan(String artist, double price) {
        return (Root<CD> root, CriteriaQuery<?> query, CriteriaBuilder builder) ->
                builder.and(
                        builder.equal(root.get("artist"), artist),
                        builder.greaterThan(root.get("price"), price)
                );
    }
}
