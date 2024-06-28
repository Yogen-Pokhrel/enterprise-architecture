package domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class Payment {
	private String payDate;
	private double amount;

	public Payment() {
	}

	public Payment(String payDate, double amount) {
		this.payDate = payDate;
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Payment{" +
				"payDate='" + payDate + '\'' +
				", amount=" + amount +
				'}';
	}
}
