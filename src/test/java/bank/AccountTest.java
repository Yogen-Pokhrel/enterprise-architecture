package bank;

import bank.domain.Account;
import bank.domain.Customer;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertAll;

public class AccountTest {
    @Test
    public void testIncrement(){
        Account account = new Account();
        account.deposit(100.0);
        assertThat( account.getBalance(), closeTo (100.0, 0.01));
    }

    @Test
    public void testWithdrawal(){
        Account account = new Account();
        account.deposit(320.0);
        account.withdraw(100.0);
        assertThat( account.getBalance(), closeTo (220.0, 0.01));
    }

    @Test
    public void testBalance(){
        Account account = new Account();
        account.deposit(320.0);
        account.withdraw(100.0);
        account.deposit(200.0);
        account.withdraw(20.0);
        assertThat( account.getBalance(), closeTo (400.0, 0.01));
    }

    @Test
    public void transfer(){
        Account account1 = new Account(123L);
        Account account2 = new Account(124L);
        Customer customer1 = new Customer("Yogen");
        account1.setCustomer(customer1);

        Customer customer2 = new Customer("John Smith");
        account2.setCustomer(customer2);
        account1.deposit(320.0);
        account1.transferFunds(account2, 39.0, "For test transaction");
        assertAll("Balances",
                () -> assertThat(account2.getBalance(), closeTo(39.0, 0.01)),
                () -> assertThat(account1.getBalance(), closeTo(281.0, 0.01))
        );
    }
}
