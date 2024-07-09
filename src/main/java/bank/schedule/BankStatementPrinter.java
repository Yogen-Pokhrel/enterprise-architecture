package bank.schedule;

import bank.dto.response.AccountListDto;
import bank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class BankStatementPrinter {

    @Autowired
    AccountService accountService;

    @Scheduled(fixedRate = 20000)
    public void print() {

        System.out.println("Bank Statement Printer: " + LocalDateTime.now());
        List<AccountListDto> allAccounts  = accountService.getAllAccounts();
        allAccounts.forEach((account) -> {
            System.out.println("Account: " + account.getAccountNumber() + " Balance: " + account.getBalance());
        });
    }
}
