package bank.repository;

import bank.domain.AccountTrace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountTraceRepository extends JpaRepository<AccountTrace, Long> {
}
