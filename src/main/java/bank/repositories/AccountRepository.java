package bank.repositories;



import org.springframework.data.jpa.repository.JpaRepository;

import bank.domain.Account;


public interface AccountRepository extends JpaRepository<Account, Long> {

}




