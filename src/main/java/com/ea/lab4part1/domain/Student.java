package com.ea.lab4part1.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;

    @Override
    public String toString() {
        return "Student{" +
                "\n\tid=" + id +
                "\n\tfirstName='" + firstName + '\'' +
                "\n\tlastName='" + lastName + '\'' +
                "\n}";
    }
}
