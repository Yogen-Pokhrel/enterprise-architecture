package com.ea.lab4part1.repository;

import com.ea.lab4part1.domain.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {
}
