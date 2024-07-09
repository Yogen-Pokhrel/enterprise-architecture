package com.lab11.dto.request;

import lombok.Data;

@Data
public class TransactionDto {
    private Double amount;
    private String currency;
    private long accountNumber;
    private TransactionAction action;
}
