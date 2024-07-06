package bank.dto.response;

import lombok.Data;

@Data
public class AccountListDto {
    Long accountNumber;
    CustomerDto customer;
    Double balance;
}
