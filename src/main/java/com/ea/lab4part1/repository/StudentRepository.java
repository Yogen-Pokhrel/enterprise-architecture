package com.ea.lab4part1.repository;

import com.ea.lab4part1.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
