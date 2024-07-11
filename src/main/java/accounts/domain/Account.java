package accounts.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Account {
	@Id
	private String accountNumber;
	private double balance;
	private String accountHolder;

	public Account() {
		super();
	}


	public Account(String accountNumber, double balance, String accountHolder) {
		super();
		this.accountNumber = accountNumber;
		this.balance = balance;
		this.accountHolder = accountHolder;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}



	public String getAccountHolder() {
		return accountHolder;
	}



	public void setAccountHolder(String accountHolder) {
		this.accountHolder = accountHolder;
	}


}
