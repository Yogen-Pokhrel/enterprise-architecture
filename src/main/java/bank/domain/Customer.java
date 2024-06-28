package bank.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

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
