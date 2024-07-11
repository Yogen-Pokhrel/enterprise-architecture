package accounts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import accounts.service.AccountDTO;
import accounts.service.AccountService;

@RestController
public class AccountController {
	@Autowired
	AccountService accountService;

	@RequestMapping("/account/{accountNumber}")
	public ResponseEntity<?> getAccount(@PathVariable("accountNumber") String accountNumber) {
		AccountDTO account = accountService.getAccount(accountNumber);
		if (account != null) {
			return new ResponseEntity<AccountDTO>(account, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Account with account number " + accountNumber + " is not available",
					HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping("/createaccount/{accountNumber}/{amount}/{accountHolder}")
	public ResponseEntity<?> createAccount(@PathVariable("accountNumber") String accountNumber,@PathVariable("amount") String sAmount, @PathVariable("accountHolder") String accountHolder) {
		accountService.createAccount(accountNumber,Double.parseDouble(sAmount),accountHolder);
		return new ResponseEntity<String>("Account with account number " + accountNumber + " is created",HttpStatus.OK);
	}
}
