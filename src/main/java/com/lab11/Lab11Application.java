package com.lab11;

import com.lab11.dto.request.CreateAccountDto;
import com.lab11.dto.request.TransactionAction;
import com.lab11.dto.request.TransactionDto;
import com.lab11.dto.request.TransferFundsDto;
import com.lab11.dto.response.AccountDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class Lab11Application implements CommandLineRunner {

	@Autowired
	Sender sender;

	public static void main(String[] args) {
		SpringApplication.run(Lab11Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		try {
			// Create an account
			CreateAccountDto createAccountDto = new CreateAccountDto();
			createAccountDto.setAccountNumber(123456789L);
			createAccountDto.setCustomerName("John Doe");
			sender.send("createAccount", createAccountDto);
			System.out.println("Create Account Request Sent: " + createAccountDto.getAccountNumber());

			createAccountDto = new CreateAccountDto();
			createAccountDto.setAccountNumber(123456788L);
			createAccountDto.setCustomerName("Yogen Pokhrel");
			sender.send("createAccount", createAccountDto);
			System.out.println("Create Account Request Sent: " + createAccountDto.getAccountNumber());

			// Perform a transaction
			TransactionDto transactionDto = new TransactionDto();
			transactionDto.setAction(TransactionAction.DEPOSIT);
			transactionDto.setCurrency("USD");
			transactionDto.setAmount(20000.0);
			transactionDto.setAccountNumber(123456789L);
			sender.send("transaction", transactionDto);
			System.out.println("Transaction request sent successfully" + transactionDto);

			// Perform a withdrawal
			transactionDto = new TransactionDto();
			transactionDto.setAction(TransactionAction.WITHDRAW);
			transactionDto.setCurrency("USD");
			transactionDto.setAmount(20.0);
			transactionDto.setAccountNumber(123456789L);
			sender.send("transaction", transactionDto);
			System.out.println("Transaction completed successfully" + transactionDto);


			try{
				// Perform a withdrawal, this should throw error as the account doesnot have enough funds
				transactionDto = new TransactionDto();
				transactionDto.setAction(TransactionAction.WITHDRAW);
				transactionDto.setCurrency("USD");
				transactionDto.setAmount(20.0);
				transactionDto.setAccountNumber(123456788L);
				sender.send("transaction", transactionDto);
				System.out.println("Transaction completed successfully" + transactionDto);
			}catch (Exception e){
				System.out.println("Error:" + e.getMessage());
			}


			// Transfer funds
			TransferFundsDto transferFundsDto = new TransferFundsDto();
			transferFundsDto.setFromAccountId(123456789L);
			transferFundsDto.setToAccountId(123456788L);
			transferFundsDto.setAmount(75.0);
			transferFundsDto.setDescription("Transfer");
			sender.send("transfer", transferFundsDto);
			System.out.println("Funds transferred successfully" + transferFundsDto);


		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}

	}
}
