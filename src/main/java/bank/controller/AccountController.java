package bank.controller;

import bank.common.ApiResponse;
import bank.common.exception.NoResourceFoundException;
import bank.configuration.Currency;
import bank.domain.Account;
import bank.dto.request.CreateAccountDto;
import bank.dto.request.TransactionAction;
import bank.dto.request.TransactionDto;
import bank.dto.request.TransferFundsDto;
import bank.dto.response.AccountDto;
import bank.dto.response.AccountListDto;
import bank.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<AccountListDto>>> getAllAccounts() {
        List<AccountListDto> allAccounts = accountService.getAllAccounts();
        return ResponseEntity.ok(ApiResponse.success(allAccounts, "Accounts fetched successfully"));
    }

    @GetMapping("/{accountNumber}")
    public ResponseEntity<ApiResponse<AccountDto>> getAccount(@PathVariable Long accountNumber) throws Exception {
        AccountDto account = accountService.getAccount(accountNumber);
        return ResponseEntity.ok(ApiResponse.success(account, "Account fetched successfully"));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<AccountDto>> createAccount(@Valid @RequestBody CreateAccountDto createAccountDto) throws Exception {
        AccountDto accountDto = accountService.createAccount(createAccountDto.getAccountNumber(), createAccountDto.getCustomerName());
        return ResponseEntity.ok(ApiResponse.success(accountDto, "Account Created Successfully"));
    }

    @PostMapping("/transfer")
    public ResponseEntity<ApiResponse<?>> transferFunds(@Valid @RequestBody TransferFundsDto transferFundsDto) throws Exception {
        accountService.transferFunds(transferFundsDto.getFromAccountId(), transferFundsDto.getToAccountId(),transferFundsDto.getAmount(), transferFundsDto.getDescription());
        return ResponseEntity.ok(ApiResponse.success(null, "Fund transferred successfully"));
    }

    @PostMapping("/{accountNumber}")
    public ResponseEntity<ApiResponse<?>> transaction(@PathVariable Long accountNumber, @Valid @RequestBody TransactionDto transactionDto) throws Exception {
        TransactionAction action = TransactionAction.valueOf(transactionDto.getAction());
        Currency currency = Currency.valueOf(transactionDto.getCurrency());
        if(action == TransactionAction.DEPOSIT){
            accountService.deposit(accountNumber, transactionDto.getAmount(), currency);
            return ResponseEntity.ok(ApiResponse.success(null, "Transaction completed Successfully"));
        }else if(action == TransactionAction.WITHDRAW){
            accountService.withdraw(accountNumber, transactionDto.getAmount(), currency);
            return ResponseEntity.ok(ApiResponse.success(null, "Transaction completed Successfully"));
        }else{
            throw new NoResourceFoundException("No resource found for action: " + action);
        }
    }
}
