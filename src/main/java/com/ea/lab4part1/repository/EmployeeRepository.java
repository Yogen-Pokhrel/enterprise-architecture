package com.ea.lab4part1.repository;

import com.ea.lab4part1.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository  extends JpaRepository<Employee, Long> {
}
