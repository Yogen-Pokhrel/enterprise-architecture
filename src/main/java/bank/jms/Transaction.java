package bank.jms;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Transaction {
    private Double amount;
    private Long account;
    private String customerName;
    private String message;
    public Transaction(Double amount, Long account, String customerName, String message) {
        this.amount = amount;
        this.account = account;
        this.message = message;
        this.customerName = customerName;
    }
    public Transaction(){}
}
