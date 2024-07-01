package bank.service;
import bank.domain.TraceRecord;
import bank.integration.EmailSender;
import bank.repositories.TraceRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bank.domain.Account;
import bank.domain.Customer;
import bank.repositories.AccountRepository;
import bank.repositories.CustomerRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BankService {
	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private EmailSender emailSender;

	@Autowired
	TraceService traceService;
	

	@Transactional
	public void createCustomerAndAccount(int customerId, String customerName, String emailAddress, String AccountNumber) throws Exception{
		try{
			Account account = new Account(AccountNumber);
			accountRepository.save(account);
			Customer customer = new Customer(customerId, customerName);
			customer.setAccount(account);
			customerRepository.saveCustomer(customer);
			emailSender.sendEmail(emailAddress, "Welcome "+customerName);
			traceService.traceRecord(customerName, AccountNumber, true);
		}catch (Exception e){
			traceService.traceRecord(customerName, AccountNumber, false);
			emailSender.sendEmail(emailAddress, "Hi, " + customerName + ", We could not create your account "+AccountNumber);
			throw e;
		}
	}
}
