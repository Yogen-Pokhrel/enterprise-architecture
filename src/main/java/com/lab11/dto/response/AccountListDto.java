package com.lab11.dto.response;

import lombok.Data;

@Data
public class AccountListDto {
    Long accountNumber;
    CustomerDto customer;
    Double balance;
}
