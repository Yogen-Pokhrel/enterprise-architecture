package domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	private String description;
	private double price;

	@Override
	public String toString() {
		return "Product{" +
				"\n\tid=" + id +
				"\n\tname='" + name + '\'' +
				"\n\tdescription='" + description + '\'' +
				"\n\tprice=" + price;
	}
}
