package repositories;

import domain.Order;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long>, JpaSpecificationExecutor<Order> {
    @Query("SELECT o.ordernr FROM Order o WHERE o.status = 'closed'")
    List<String> findOrderNumbersByStatusClosed();

    @Query("SELECT o.ordernr FROM Order o WHERE o.customer.address.city = :city")
    List<String> findOrderNumbersByCustomerCity(@Param("city") String city);
}
