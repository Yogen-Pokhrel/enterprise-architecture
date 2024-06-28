package bank.service;

import java.util.Collection;

import bank.domain.Account;
import bank.domain.Customer;
import bank.jms.IJMSSender;
import bank.logging.ILogger;
import bank.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AccountService implements IAccountService {
	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private ICurrencyConverter currencyConverter;

	@Autowired
	private IJMSSender jmsSender;

	@Autowired
	private ILogger logger;

	public Account createAccount(long accountNumber, String customerName) {
		Account account = new Account(accountNumber);
		Customer customer = new Customer(customerName);
		account.setCustomer(customer);
		account = accountRepository.save(account);
		logger.log("createAccount with parameters accountNumber= "+accountNumber+" , customerName= "+customerName);
		return account;
	}

	public void deposit(long accountNumber, double amount) throws Exception {
		Account account = accountRepository.findById(accountNumber).orElseThrow(()->new Exception("Account with accountNumber= "+accountNumber+" not found"));
		account.deposit(amount);
		accountRepository.save(account);
		logger.log("deposit with parameters accountNumber= "+accountNumber+" , amount= "+amount);
		if (amount > 10000){
			jmsSender.sendJMSMessage("Deposit of $ "+amount+" to account with accountNumber= "+accountNumber);
		}
	}

	public Account getAccount(long accountNumber) throws Exception {
		return accountRepository.findById(accountNumber).orElseThrow(()->new Exception("Account with accountNumber= "+accountNumber+" not found"));
	}

	public Collection<Account> getAllAccounts() {
		return accountRepository.findAll();
	}

	public void withdraw(long accountNumber, double amount) throws Exception {
		Account account = accountRepository.findById(accountNumber).orElseThrow(()->new Exception("Account with accountNumber= "+accountNumber+" not found"));
		account.withdraw(amount);
		accountRepository.save(account);
		logger.log("withdraw with parameters accountNumber= "+accountNumber+" , amount= "+amount);
	}

	public void depositEuros(long accountNumber, double amount) throws Exception {
		Account account = accountRepository.findById(accountNumber).orElseThrow(()->new Exception("Account with accountNumber= "+accountNumber+" not found"));
		double amountDollars = currencyConverter.euroToDollars(amount);
		account.deposit(amountDollars);
		accountRepository.save(account);
		logger.log("depositEuros with parameters accountNumber= "+accountNumber+" , amount= "+amount);
		if (amountDollars > 10000){
			jmsSender.sendJMSMessage("Deposit of $ "+amount+" to account with accountNumber= "+accountNumber);
		}
	}

	public void withdrawEuros(long accountNumber, double amount) throws Exception {
		Account account = accountRepository.findById(accountNumber).orElseThrow(()->new Exception("Account with accountNumber= "+accountNumber+" not found"));
		double amountDollars = currencyConverter.euroToDollars(amount);
		account.withdraw(amountDollars);
		accountRepository.save(account);
		logger.log("withdrawEuros with parameters accountNumber= "+accountNumber+" , amount= "+amount);
	}

	public void transferFunds(long fromAccountNumber, long toAccountNumber, double amount, String description) throws Exception {
		Account fromAccount = accountRepository.findById(fromAccountNumber).orElseThrow(()->new Exception("Account not found with accountNumber= "+fromAccountNumber));
		Account toAccount = accountRepository.findById(toAccountNumber).orElseThrow(()->new Exception("Account not found with accountNumber= "+toAccountNumber));
		fromAccount.transferFunds(toAccount, amount, description);
		accountRepository.save(fromAccount);
		accountRepository.save(toAccount);
		logger.log("transferFunds with parameters fromAccountNumber= "+fromAccountNumber+" , toAccountNumber= "+toAccountNumber+" , amount= "+amount+" , description= "+description);
		if (amount > 10000){
			jmsSender.sendJMSMessage("TransferFunds of $ "+amount+" from account with accountNumber= "+fromAccount+" to account with accountNumber= "+toAccount);
		}
	}
}
