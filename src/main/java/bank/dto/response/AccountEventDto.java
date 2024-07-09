package bank.dto.response;

import lombok.Data;

@Data
public class AccountEventDto {
    private String message;
    private AccountDto accountDto;
    public AccountEventDto() {}
    public AccountEventDto(String message, AccountDto accountDto) {
        this.message = message;
        this.accountDto = accountDto;
    }
}
