package com.lab11.dto.request;

import lombok.Data;

@Data
public class CreateAccountDto {
    private Long accountNumber;
    private String customerName;
}
