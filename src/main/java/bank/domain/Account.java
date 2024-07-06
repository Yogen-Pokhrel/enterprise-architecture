package bank.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Entity
@Getter
@Setter
public class Account {
	@Id
	long accountNumber;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "")
	Collection<AccountEntry> entryList = new ArrayList<AccountEntry>();

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "customer_id")
	Customer customer;

	public Account(){}
	public Account (long accountNumber){
		this.accountNumber = accountNumber;
	}
	public double getBalance() {
		double balance=0;
		for (AccountEntry entry : entryList) {
			balance+=entry.getAmount();
		}
		return balance;
	}
	public void deposit(double amount){
		AccountEntry entry = new AccountEntry(new Date(), amount, "deposit", "", "");
		entryList.add(entry);
	}
	public void withdraw(double amount){
		AccountEntry entry = new AccountEntry(new Date(), -amount, "withdraw", "", "");
		entryList.add(entry);	
	}

	private void addEntry(AccountEntry entry){
		entryList.add(entry);
	}

	public void transferFunds(Account toAccount, double amount, String description){
		AccountEntry fromEntry = new AccountEntry(new Date(), -amount, description, ""+toAccount.getAccountNumber(), toAccount.getCustomer().getName());
		AccountEntry toEntry = new AccountEntry(new Date(), amount, description, ""+toAccount.getAccountNumber(), toAccount.getCustomer().getName());
		entryList.add(fromEntry);	
		toAccount.addEntry(toEntry);
	}

	@Override
	public String toString() {
		return "Account [account number=" + accountNumber + ", entryListCount=" + entryList.size() + " " + customer + "]";
	}
}
