package com.ea.lab4part1.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Getter
@Setter
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "school_id")
    @OrderColumn(name = "sequence")
    private Map<Long, Student> students = new HashMap<>();

    public void addStudent(Student student) {
        students.put(student.getId(), student);
    }

    @Override
    public String toString() {
        return "School{" +
                "\n\tid=" + id +
                "\n\tname='" + name + '\'' +
                "\n\tstudents=" + students +
                "\n}";
    }
}
