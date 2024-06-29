package bank.dto;

import lombok.Data;

import java.util.Collection;

@Data
public class AccountDto {
    long accountnumber;
    CustomerDto customer;
    Collection<AccountEntryDto> entryList;

    //
    public double getBalance() {
        double balance=0;
        for (AccountEntryDto entry : entryList) {
            balance+=entry.getAmount();
        }
        return balance;
    }
}

