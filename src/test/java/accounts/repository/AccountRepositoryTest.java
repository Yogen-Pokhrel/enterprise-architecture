package accounts.repository;

import accounts.domain.Account;
import accounts.repositories.AccountRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AccountRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void findByAccountHolder() {
        Account account = new Account("123", 244, "Yogen Pokhrel");
        entityManager.persist(account);
        entityManager.flush();
        Account found = accountRepository.findByAccountHolder("Yogen Pokhrel");
        assertThat(found.getAccountHolder()).isEqualTo("Yogen Pokhrel");
    }
}
