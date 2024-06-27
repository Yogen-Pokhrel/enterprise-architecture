package com.ea.lab4part1.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String flightNumber;
    @Column(name = "flight_from")
    private String from;

    @Column(name = "order_by")
    private int orderBy;

    @Column(name = "flight_to")
    private String to;
    private LocalDateTime date;

    @Override
    public String toString() {
        return "Flight{" +
                "\n\tid=" + id +
                "\n\tflightNumber='" + flightNumber + '\'' + "," +
                "\n\tfrom='" + from + '\'' +  "," +
                "\n\tto='" + to + '\'' +  "," +
                "\n\tdate=" + date +
                "\n}";
    }
}
