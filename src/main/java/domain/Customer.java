package domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

@Entity
@Getter
@Setter
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String firstname;
	private String lastname;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "customer_id")
	private Address address;

	@OneToMany(cascade = CascadeType.PERSIST)
	private Collection<Order> theOrders = new ArrayList<Order>();

	public Customer() {
	}

	public Customer(String firstname, String lastname, String street, String city, String zip) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.address = new Address(street, city, zip);
	}

	public boolean addOrder(Order order) {
		boolean added = theOrders.add(order); 
		if (added) {
			order.setCustomer(this);
		}
		return added;
	}

	public boolean removeOrder(Order order) {
		boolean removed = theOrders.remove(order);
		if (removed) {
			theOrders.remove(order);
		}
		return removed;
	}

}
