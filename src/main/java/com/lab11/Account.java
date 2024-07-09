package com.lab11;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Account {
	long accountNumber;
	Customer customer;

	@Override
	public String toString() {
		return "Account [account number=" + accountNumber + " " + customer + "]";
	}
}
