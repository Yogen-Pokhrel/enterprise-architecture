package app;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    //if eager fetch is needed
    @Query("SELECT c, c.account from Customer c")
    List<Customer> findAllEager();

    @Modifying
    @Transactional
    @Query("UPDATE Customer c SET c.name = :name")
    void updateCustomerName(@Param("name") String customerName);
}




