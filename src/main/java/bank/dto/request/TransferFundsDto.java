package bank.dto.request;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class TransferFundsDto {
    @Positive(message = "Account Number must be a positive number")
    @Digits(integer = 19, fraction = 0, message = "Account Number must not have more than 19 digits")
    @Min(value = 10000L, message = "Account Number must be at least 5 digits long")
    private Long fromAccountId;

    @Positive(message = "Account Number must be a positive number")
    @Digits(integer = 19, fraction = 0, message = "Account Number must not have more than 19 digits")
    @Min(value = 10000L, message = "Account Number must be at least 5 digits long")
    private Long toAccountId;

    @Positive(message = "Amount must be a positive number")
    @Min(value = 1L, message = "Amount must be grater than 1")
    private Double amount;
    private String description;
}
