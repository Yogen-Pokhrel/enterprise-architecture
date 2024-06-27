package com.ea.lab4part1.repository;

import com.ea.lab4part1.domain.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
