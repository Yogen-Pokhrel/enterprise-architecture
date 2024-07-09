package bank.schedule;

import bank.dto.response.AccountListDto;
import bank.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class BankStatementPrinter {

    @Autowired
    AccountService accountService;

    Logger logger = LoggerFactory.getLogger(BankStatementPrinter.class);

    @Scheduled(fixedRate = 20000)
    public void print() {

        logger.info("Bank Statement Printer: {}", LocalDateTime.now());
        List<AccountListDto> allAccounts  = accountService.getAllAccounts();
        allAccounts.forEach((account) -> {
            logger.info("Account: {} Balance: {}", account.getAccountNumber(), account.getBalance());
        });
    }
}
