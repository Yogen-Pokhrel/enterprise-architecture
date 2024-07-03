package bank.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Customer {
	private String name;

	public Customer() {}
	public Customer(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Customer{" +
				"name='" + name + '\'' +
				'}';
	}
}
