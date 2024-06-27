package com.ea.lab4part1.repository;

import com.ea.lab4part1.domain.School;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolRepository extends JpaRepository<School, Long> {
}
