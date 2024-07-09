package bank.kafka;

import bank.common.ApiResponse;
import bank.common.exception.NoResourceFoundException;
import bank.configuration.Currency;
import bank.dto.request.CreateAccountDto;
import bank.dto.request.TransactionAction;
import bank.dto.request.TransactionDto;
import bank.dto.request.TransferFundsDto;
import bank.service.AccountService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;


@Service
public class Receiver {

    @Autowired
    AccountService accountService;

    @KafkaListener(topics = {"createAccount"})
    public void createAccount(@Payload String data) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            CreateAccountDto createAccountDto = objectMapper.readValue(data, CreateAccountDto.class);
            accountService.createAccount(createAccountDto.getAccountNumber(), createAccountDto.getCustomerName());
            System.out.println("Kafka Created account " + createAccountDto.getAccountNumber());
        } catch (Exception e) {
            System.out.println("Kafka receiver: Cannot convert : " + data +" to a CreateAccountDto object");
        }
    }

    @KafkaListener(topics = {"transfer"})
    public void transferFunds(@Payload String data) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            TransferFundsDto transferFundsDto = objectMapper.readValue(data, TransferFundsDto.class);
            accountService.transferFunds(transferFundsDto.getFromAccountId(), transferFundsDto.getToAccountId(),transferFundsDto.getAmount(), transferFundsDto.getDescription());
            System.out.println("Kafka Funds transferred" + transferFundsDto.getAmount());
        } catch (Exception e) {
            System.out.println("Kafka receiver: Cannot convert : " + data +" to a TransferFundsDto object");
        }
    }

    @KafkaListener(topics = {"transaction"})
    public void transaction(@Payload String data) {
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println("Kafka transaction" + data);
        try {
            TransactionDto transactionDto = objectMapper.readValue(data, TransactionDto.class);
            TransactionAction action = TransactionAction.valueOf(transactionDto.getAction());
            Currency currency = Currency.valueOf(transactionDto.getCurrency());
            if(action == TransactionAction.DEPOSIT){
                accountService.deposit(transactionDto.getAccountNumber(), transactionDto.getAmount(), currency);
            }else if(action == TransactionAction.WITHDRAW){
                accountService.withdraw(transactionDto.getAccountNumber(), transactionDto.getAmount(), currency);
            }else{
                throw new NoResourceFoundException("No resource found for action: " + action);
            }
        } catch (Exception e) {
            System.out.println("Kafka receiver: Cannot convert : " + data +" to a TransactionDto object");
            System.out.println(e.getMessage());
        }
    }
}
