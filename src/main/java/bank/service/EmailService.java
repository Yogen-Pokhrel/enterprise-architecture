package bank.service;

import bank.dto.response.AccountEventDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    MailProperties mailProperties;

    @EventListener
    public void onAccountChangeEvent(AccountEventDto accountDto) {
        System.out.println("Account changed event received: ");
        System.out.println(accountDto.getMessage());
        System.out.println("Sending email to account holder: "  + accountDto.getAccountDto().getCustomer().getName() + " with account: " + accountDto.getAccountDto().getAccountNumber());
    }
}
