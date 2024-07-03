package bank.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Getter
@Setter
public class AccountEntry {
	private Date date;
	private double amount;
	private String description;
	private String fromAccountNumber;
	private String fromPersonName;
	
	public AccountEntry() {}

	public AccountEntry(Date date, double amount, String description, String fromAccountNumber, String fromPersonName) {
		super();
		this.date = date;
		this.amount = amount;
		this.description = description;
		this.fromAccountNumber = fromAccountNumber;
		this.fromPersonName = fromPersonName;
	}

	@Override
	public String toString() {
		return "AccountEntry{" +
				", date=" + date +
				", amount=" + amount +
				", description='" + description + '\'' +
				", fromAccountNumber='" + fromAccountNumber + '\'' +
				", fromPersonName='" + fromPersonName + '\'' +
				'}';
	}
}
