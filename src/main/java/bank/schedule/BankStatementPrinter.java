package bank.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class BankStatementPrinter {
    @Scheduled(fixedRate = 20000)
    public void print() {
        System.out.println("Bank Statement Printer");
    }
}
