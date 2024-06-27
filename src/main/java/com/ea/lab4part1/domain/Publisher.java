package com.ea.lab4part1.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Override
    public String toString() {
        return "Publisher{\n" +
                "\t" + "id=" + id + ",\n" +
                "\t" + "name='" + name + "'\n" +
                '}';
    }
}
