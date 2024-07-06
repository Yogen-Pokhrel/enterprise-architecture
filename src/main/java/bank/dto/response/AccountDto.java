package bank.dto.response;

import lombok.Data;

import java.util.Collection;
import java.util.List;

@Data
public class AccountDto {
    Long accountNumber;
    CustomerDto customer;
    Double balance;
    List<AccountEntryDto> entryList;
}

