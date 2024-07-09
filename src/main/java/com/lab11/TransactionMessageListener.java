package com.lab11;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class TransactionMessageListener {

    @JmsListener(destination = "transaction")
    public void receiveLargeTransaction(final String data) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Transaction transactionData = objectMapper.readValue(data, Transaction.class);
            System.out.println("JMS receiver received message:" + transactionData);

        } catch (IOException e) {
            System.out.println("JMS receiver: Cannot convert : " + data +" to a transaction object");
            System.out.println(e.getMessage());
        }
    }
}
