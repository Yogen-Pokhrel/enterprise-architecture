package domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;


@Entity
@Getter
@Setter
@Table(name = "customer_order")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String ordernr;
	private String date;
	private String status;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "customer_id")
	private Customer customer;


	@OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name = "order_id")
	private Collection<OrderLine> orderlines = new ArrayList<OrderLine>();

	public Order() {
	}

	public Order(String ordernr, String date, String status) {
		this.ordernr = ordernr;
		this.date = date;
		this.status = status;
	}

	public Collection<OrderLine> getOrderlines() {
		return Collections.unmodifiableCollection(orderlines);
	}

	public boolean addOrderLine(OrderLine ol) {
		return orderlines.add(ol);
	}
}
