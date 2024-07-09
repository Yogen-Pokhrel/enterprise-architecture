package com.lab11.dto.request;

import lombok.Data;

@Data
public class TransferFundsDto {
    private Long fromAccountId;
    private Long toAccountId;
    private Double amount;
    private String description;
}
