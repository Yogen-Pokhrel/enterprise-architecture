package com.ea.lab4part1.repository;

import com.ea.lab4part1.domain.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {
}
