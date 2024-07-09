package bank.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
public class AccountTrace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime timestamp;
    private long accountNumber;
    private String operation;
    private double amount;

    public AccountTrace() {
    }

    public AccountTrace(LocalDateTime timestamp, long accountNumber, String operation, double amount) {
        this.timestamp = timestamp;
        this.accountNumber = accountNumber;
        this.operation = operation;
        this.amount = amount;
    }

}
