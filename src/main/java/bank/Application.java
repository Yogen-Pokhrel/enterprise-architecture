package bank;

import java.util.Collection;
import java.util.List;

import bank.configuration.Currency;
import bank.dto.request.CreateAccountDto;
import bank.dto.request.TransactionDto;
import bank.dto.request.TransferFundsDto;
import bank.dto.response.AccountDto;
import bank.dto.response.AccountEntryDto;
import bank.dto.response.CustomerDto;
import bank.service.AccountService;
import bank.service.IAccountService;
import bank.service.MailProperties;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
@EnableConfigurationProperties(MailProperties.class)
public class Application implements CommandLineRunner {

	Logger logger = LoggerFactory.getLogger(Application.class);

	@Autowired
	Client client;

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		try {
			// Create an account
			CreateAccountDto createAccountDto = new CreateAccountDto();
			createAccountDto.setAccountNumber(123456789L);
			createAccountDto.setCustomerName("John Doe");
			AccountDto createdAccount = client.createAccount(createAccountDto);
            logger.info("Created Account: {}", createdAccount);

			createAccountDto = new CreateAccountDto();
			createAccountDto.setAccountNumber(123456788L);
			createAccountDto.setCustomerName("Yogen Pokhrel");
			createdAccount = client.createAccount(createAccountDto);
			logger.info("Created Account: {}", createdAccount);

			// Perform a transaction
			TransactionDto transactionDto = new TransactionDto();
			transactionDto.setAction("DEPOSIT");
			transactionDto.setCurrency("USD");
			transactionDto.setAmount(20000.0);
			client.transaction(123456789L, transactionDto);
			logger.info("Transaction completed successfully");

			// Perform a withdrawal
			transactionDto = new TransactionDto();
			transactionDto.setAction("WITHDRAW");
			transactionDto.setCurrency("USD");
			transactionDto.setAmount(20.0);
			client.transaction(123456789L, transactionDto);
			logger.info("Transaction completed successfully");


			try{
				// Perform a withdrawal, this should throw error as the account doesnot have enough funds
				transactionDto = new TransactionDto();
				transactionDto.setAction("WITHDRAW");
				transactionDto.setCurrency("USD");
				transactionDto.setAmount(20.0);
				client.transaction(123456788L, transactionDto);
				logger.info("Transaction completed successfully");
			}catch (Exception e){
                logger.error("Error:{}", e.getMessage());
			}

			// Get the account
			AccountDto retrievedAccount = client.getAccount(123456789L);
            logger.info("Retrieved Account: {}", retrievedAccount);

			// Get all accounts
			List<AccountDto> accounts = client.getAllAccounts();
			logger.info("All Accounts: {}", accounts);

			// Transfer funds
			TransferFundsDto transferFundsDto = new TransferFundsDto();
			transferFundsDto.setFromAccountId(123456789L);
			transferFundsDto.setToAccountId(123456788L);
			transferFundsDto.setAmount(75.0);
			transferFundsDto.setDescription("Transfer");
			client.transferFunds(transferFundsDto);
			logger.info("Funds transferred successfully");

			// Get the account
			retrievedAccount = client.getAccount(123456788L);
			logger.info("Retrieved Account: {}", retrievedAccount);


		} catch (Exception e) {
            logger.error("Error: {}", e.getMessage());
//			e.printStackTrace();
		}
	}
}


