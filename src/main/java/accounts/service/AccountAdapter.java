package accounts.service;

import accounts.domain.Account;

public class AccountAdapter {

	public static AccountDTO getAccountDto(Account account) {
		AccountDTO accountDTO = new AccountDTO(account.getAccountNumber(), account.getBalance(), account.getAccountHolder());
		return accountDTO;
	}
}
