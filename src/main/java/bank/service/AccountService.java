package bank.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import bank.domain.Account;
import bank.domain.AccountEntry;
import bank.domain.Customer;
import bank.dto.AccountDto;
import bank.dto.AccountEntryDto;
import bank.helpers.ListMapper;
import bank.jms.IJMSSender;
import bank.logging.ILogger;
import bank.repository.AccountRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
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

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	ListMapper listMapper;

	@Transactional
	public AccountDto createAccount(long accountNumber, String customerName) {
		Account account = new Account(accountNumber);
		Customer customer = new Customer(customerName);
		account.setCustomer(customer);
		account = accountRepository.save(account);
		logger.log("createAccount with parameters accountNumber= "+accountNumber+" , customerName= "+customerName);
		return modelMapper.map(account, AccountDto.class);
	}

	@Transactional
	public void deposit(long accountNumber, double amount) throws Exception {
		Account account = accountRepository.findById(accountNumber).orElseThrow(()->new Exception("Account with accountNumber= "+accountNumber+" not found"));
		account.deposit(amount);
		accountRepository.save(account);
		logger.log("deposit with parameters accountNumber= "+accountNumber+" , amount= "+amount);
		if (amount > 10000){
			jmsSender.sendJMSMessage("Deposit of $ "+amount+" to account with accountNumber= "+accountNumber);
		}
	}

	@Transactional
	public AccountDto getAccount(long accountNumber) throws Exception {
		Account account = accountRepository.findById(accountNumber).orElseThrow(()->new Exception("Account with accountNumber= "+accountNumber+" not found"));
		return modelMapper.map(account, AccountDto.class);
	}

	@Transactional
	@SuppressWarnings("unchecked")
	public Collection<AccountDto> getAllAccounts() {
		Collection<Account> accounts = accountRepository.findAll();
		return (Collection<AccountDto>)listMapper.mapCollection(accounts, AccountDto.class);
	}

	@Transactional
	public void withdraw(long accountNumber, double amount) throws Exception {
		Account account = accountRepository.findById(accountNumber).orElseThrow(()->new Exception("Account with accountNumber= "+accountNumber+" not found"));
		account.withdraw(amount);
		accountRepository.save(account);
		logger.log("withdraw with parameters accountNumber= "+accountNumber+" , amount= "+amount);
	}

	@Transactional
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

	@Transactional
	public void withdrawEuros(long accountNumber, double amount) throws Exception {
		Account account = accountRepository.findById(accountNumber).orElseThrow(()->new Exception("Account with accountNumber= "+accountNumber+" not found"));
		double amountDollars = currencyConverter.euroToDollars(amount);
		account.withdraw(amountDollars);
		accountRepository.save(account);
		logger.log("withdrawEuros with parameters accountNumber= "+accountNumber+" , amount= "+amount);
	}

	@Transactional
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

	public double calculateBalance(Collection<AccountEntryDto> entryList) {
		double balance=0;
		for (AccountEntryDto entry : entryList) {
			balance+=entry.getAmount();
		}
		return balance;
	}
}
