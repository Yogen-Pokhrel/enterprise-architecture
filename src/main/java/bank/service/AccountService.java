package bank.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import bank.common.exception.DuplicateResourceException;
import bank.configuration.Currency;
import bank.domain.Account;
import bank.domain.Customer;
import bank.dto.response.AccountDto;
import bank.dto.response.AccountListDto;
import bank.jms.IJMSSender;
import bank.jms.Transaction;
import bank.logging.ILogger;
import bank.repository.AccountRepository;
import jakarta.transaction.Transactional;
import org.apache.coyote.BadRequestException;
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

	@Transactional
	public AccountDto createAccount(long accountNumber, String customerName) throws Exception{
		Account existingAccount = accountRepository.findById(accountNumber).orElse(null);
		if (existingAccount != null) {
			throw new DuplicateResourceException("Account already exist with provided account number");
		}
		Account account = new Account(accountNumber);
		Customer customer = new Customer(customerName);
		account.setCustomer(customer);
		account = accountRepository.save(account);
		logger.log("createAccount with parameters accountNumber= "+accountNumber+" , customerName= "+customerName);
		return modelMapper.map(account, AccountDto.class);
	}

	@Transactional
	public void deposit(long accountNumber, double amount, Currency currency) throws Exception {
		if(currency == Currency.EUR){
			amount = currencyConverter.euroToDollars(amount);
		}
		Account account = accountRepository.findById(accountNumber).orElseThrow(()->new Exception("Account with accountNumber= "+accountNumber+" not found"));
		account.deposit(amount);
		accountRepository.save(account);
		logger.log("deposit with parameters accountNumber= "+accountNumber+" , amount= "+amount);
		double amount_euro = currencyConverter.dollarsToEuros(amount);
		if (amount_euro >= 10000){
			Transaction transaction = new Transaction(amount_euro, account.getAccountNumber(), account.getCustomer().getName(), "Deposit of $ "+amount+" to account with accountNumber= "+accountNumber);
			jmsSender.sendJMSMessage(transaction);
		}
	}

	@Transactional
	public AccountDto getAccount(long accountNumber) throws Exception {
		Account account = accountRepository.findById(accountNumber).orElseThrow(()->new Exception("Account with accountNumber= "+accountNumber+" not found"));
		AccountDto accountDto = modelMapper.map(account, AccountDto.class);
		accountDto.setBalance(account.getBalance());
		return accountDto;
	}

	@Transactional
	public List<AccountListDto> getAllAccounts() {
		Collection<Account> accounts = accountRepository.findAll();
		List<AccountListDto> allAccounts = new ArrayList<>();
		for (Account account : accounts) {
			AccountListDto accountDto = modelMapper.map(account, AccountListDto.class);
			accountDto.setBalance(account.getBalance());
			allAccounts.add(accountDto);
		}
		return allAccounts;
	}

	@Transactional
	public void withdraw(long accountNumber, double amount, Currency currency) throws Exception {
		if(currency == Currency.EUR){
			amount = currencyConverter.euroToDollars(amount);
		}
		Account account = accountRepository.findById(accountNumber).orElseThrow(()->new Exception("Account with accountNumber= "+accountNumber+" not found"));
		double balance = account.getBalance();
		if(balance < amount){
			throw new BadRequestException("Insufficient funds");
		}
		account.withdraw(amount);
		accountRepository.save(account);
		logger.log("withdraw with parameters accountNumber= "+accountNumber+" , amount= USD"+amount);
	}

	@Transactional
	public void transferFunds(long fromAccountNumber, long toAccountNumber, double amount, String description) throws Exception {
		Account fromAccount = accountRepository.findById(fromAccountNumber).orElseThrow(()->new Exception("Account not found with accountNumber= "+fromAccountNumber));
		Account toAccount = accountRepository.findById(toAccountNumber).orElseThrow(()->new Exception("Account not found with accountNumber= "+toAccountNumber));
		double balance = fromAccount.getBalance();
		if(balance < amount){
			throw new BadRequestException("Insufficient funds");
		}
		fromAccount.transferFunds(toAccount, amount, description);
		accountRepository.save(fromAccount);
		accountRepository.save(toAccount);
		logger.log("transferFunds with parameters fromAccountNumber= "+fromAccountNumber+" , toAccountNumber= "+toAccountNumber+" , amount= "+amount+" , description= "+description);
		if (amount > 10000){
			jmsSender.sendJMSMessage("TransferFunds of $ "+amount+" from account with accountNumber= "+fromAccount+" to account with accountNumber= "+toAccount);
		}
	}
}
