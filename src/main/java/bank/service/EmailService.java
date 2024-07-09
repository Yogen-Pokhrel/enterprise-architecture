package bank.service;

import bank.dto.response.AccountEventDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    MailProperties mailProperties;

    Logger logger = LoggerFactory.getLogger(EmailService.class);

    @EventListener
    public void onAccountChangeEvent(AccountEventDto accountDto) {
        logger.info("Account changed event received: ");
        logger.info(accountDto.getMessage());
        logger.info("Sending email to account holder: {} with account: {}", accountDto.getAccountDto().getCustomer().getName(), accountDto.getAccountDto().getAccountNumber());
    }
}
