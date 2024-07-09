package com.lab11.dto.response;

import lombok.Data;

import java.util.Date;

@Data
public class AccountEntryDto {
    long id;
    Date date;
    double amount;
    String description;
    String fromAccountNumber;
    String fromPersonName;
}
