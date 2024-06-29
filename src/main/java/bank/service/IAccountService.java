package bank.service;

import java.util.Collection;

import bank.domain.Account;
import bank.dto.AccountDto;
import bank.dto.AccountEntryDto;


public interface IAccountService {
    public AccountDto createAccount(long accountNumber, String customerName) throws Exception;
    public AccountDto getAccount(long accountNumber) throws Exception;
    public Collection<AccountDto> getAllAccounts() throws Exception;
    public void deposit (long accountNumber, double amount) throws Exception;
    public void withdraw (long accountNumber, double amount) throws Exception;
    public void depositEuros (long accountNumber, double amount) throws Exception;
    public void withdrawEuros (long accountNumber, double amount) throws Exception;
    public void transferFunds(long fromAccountNumber, long toAccountNumber, double amount, String description) throws Exception;
    public double calculateBalance (Collection<AccountEntryDto> accountEntry) throws Exception;
}
