package com.ea.lab4part1.service;

import com.ea.lab4part1.domain.Passenger;
import com.ea.lab4part1.repository.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PassengerService {

    @Autowired
    private PassengerRepository passengerRepository;

    public List<Passenger> findAll() {
        return passengerRepository.findAll();
    }

    public Passenger findById(Long id) {
        return passengerRepository.findById(id).get();
    }

    public Passenger save(Passenger passenger) {
        return passengerRepository.save(passenger);
    }
}
