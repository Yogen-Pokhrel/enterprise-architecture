package domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Appointment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String appdate;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "patient")
	private Patient patient;

	@Embedded
	private Payment payment;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "doctor")
	private Doctor doctor;

	public Appointment() {
	}

	public Appointment(String appdate, Patient patient, Payment payment,
			Doctor doctor) {
		this.appdate = appdate;
		this.patient = patient;
		this.payment = payment;
		this.doctor = doctor;
	}

	@Override
	public String toString() {
		return "Appointment{" +
				"\n\tid=" + id +
				"\n\tappdate='" + appdate + '\'' +
				"\n\tpatient=" + patient +
				"\n\tpayment=" + payment +
				"\n\tdoctor=" + doctor +
				"\n}";
	}
}
