package com.ea.lab4part1.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Passenger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "passenger_id")
    @OrderColumn(name = "order_by")
    private List<Flight> flights = new ArrayList<>();

    public void addFlight(Flight flight) {
        flights.add(flight);
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "\n\tid=" + id +
                "\n\tname='" + name + '\'' + "," +
                "\n\tflights=" + flights + "," +
                "\n}";
    }
}
