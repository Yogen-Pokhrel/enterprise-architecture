package com.ea.lab4part1.service;

import com.ea.lab4part1.domain.School;
import com.ea.lab4part1.repository.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchoolService {

    @Autowired
    private SchoolRepository schoolRepository;

    public List<School> findAll() {
        return schoolRepository.findAll();
    }

    public School save(School school) {
        return schoolRepository.save(school);
    }

    public School findById(Long id) {
        return schoolRepository.findById(id).orElse(null);
    }
}
