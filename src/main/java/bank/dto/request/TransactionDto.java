package bank.dto.request;

import bank.configuration.Currency;
import bank.helpers.validators.ValidEnum;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class TransactionDto {
    @Positive(message = "Amount must be a positive number")
    @Min(value = 1L, message = "Amount must be grater than 1")
    private Double amount;

    @ValidEnum(enumClass = Currency.class, message = "Please provide a valid currency")
    private String currency;

    @ValidEnum(enumClass = TransactionAction.class, message = "Please provide a valid action type")
    private String action;

    private long accountNumber;

}
