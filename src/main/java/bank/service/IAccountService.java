package bank.service;

import java.util.Collection;

import bank.configuration.Currency;
import bank.dto.response.AccountDto;
import bank.dto.response.AccountEntryDto;
import bank.dto.response.AccountListDto;


public interface IAccountService {
    public AccountDto createAccount(long accountNumber, String customerName) throws Exception;
    public AccountDto getAccount(long accountNumber) throws Exception;
    public Collection<AccountListDto> getAllAccounts() throws Exception;
    public void deposit (long accountNumber, double amount, Currency currency) throws Exception;
    public void withdraw (long accountNumber, double amount, Currency currency) throws Exception;
    public void transferFunds(long fromAccountNumber, long toAccountNumber, double amount, String description) throws Exception;
}
