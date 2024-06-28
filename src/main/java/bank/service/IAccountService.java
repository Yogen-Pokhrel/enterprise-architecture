package bank.service;

import java.util.Collection;

import bank.domain.Account;



public interface IAccountService {
    public Account createAccount(long accountNumber, String customerName) throws Exception;
    public Account getAccount(long accountNumber) throws Exception;
    public Collection<Account> getAllAccounts() throws Exception;
    public void deposit (long accountNumber, double amount) throws Exception;
    public void withdraw (long accountNumber, double amount) throws Exception;
    public void depositEuros (long accountNumber, double amount) throws Exception;
    public void withdrawEuros (long accountNumber, double amount) throws Exception;
    public void transferFunds(long fromAccountNumber, long toAccountNumber, double amount, String description) throws Exception;
}
