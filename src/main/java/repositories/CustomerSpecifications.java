package repositories;

import domain.Customer;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class CustomerSpecifications {
    public static Specification<Customer> livesInCity(String city) {
        return (Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder builder) ->
                builder.equal(root.get("address").get("city"), city);
    }
}
