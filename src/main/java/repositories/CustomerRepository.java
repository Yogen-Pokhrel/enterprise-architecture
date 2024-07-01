package repositories;

import domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>, JpaSpecificationExecutor<Customer> {
    List<Customer> findAll();

    List<Customer> findByAddressZip(String zip);

    @Query(name = "Customer.findAllCustomersFromUSA")
    List<Customer> findAllCustomersFromUSA();

    @Query("SELECT c FROM Customer c JOIN c.theOrders o JOIN o.orderlines ol JOIN ol.product p WHERE TYPE(p) = DVD AND p.name = :name")
    List<Customer> findCustomersByOrderedDVDName(@Param("name") String name);

    @Query("SELECT c.firstname, c.lastname FROM Customer c WHERE c.address.city = 'Amsterdam'")
    List<Object[]> findFirstAndLastNamesByCityAmsterdam();
}
