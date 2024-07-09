package com.lab11;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
public class Transaction {
    private Double amount;
    private Long account;
    private String customerName;
    private String message;
    public Transaction(){}

    @Override
    public String toString() {
        return "Transaction{" +
                "amount=" + amount +
                ", account=" + account +
                ", customerName='" + customerName + '\'' +
                ", description='" + message + '\'' +
                '}';
    }
}
