package bank.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Customer {

	@Id
	private long id;
	private String name;
	
    @ManyToOne
    @JoinColumn(name="account_id")
    private Account account;

	protected Customer() {}

	public Customer(long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	@Override
	public String toString() {
		return "Customer{" +
				"id=" + id +
				", name='" + name + '\'' +
				", account=" + account +
				'}';
	}
}


