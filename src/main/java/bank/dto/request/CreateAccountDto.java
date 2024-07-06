package bank.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class CreateAccountDto {
    @Positive(message = "Account Number must be a positive number")
    @Digits(integer = 19, fraction = 0, message = "Account Number must not have more than 19 digits")
    @Min(value = 10000L, message = "Account Number must be at least 5 digits long")
    private Long accountNumber;

    @NotBlank(message = "Customer name is mandatory")
    @Size(min = 1, max = 50, message = "Customer name must be between 1 and 120 characters")
    private String customerName;
}
