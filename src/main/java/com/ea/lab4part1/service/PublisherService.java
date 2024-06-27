package com.ea.lab4part1.service;

import com.ea.lab4part1.domain.Publisher;
import com.ea.lab4part1.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublisherService {

    @Autowired
    private PublisherRepository publisherRepository;

    public Publisher save(Publisher publisher) {
        return publisherRepository.save(publisher);
    }

    public List<Publisher> findAll() {
        return publisherRepository.findAll();
    }

    public Publisher findById(Long id) {
        return publisherRepository.findById(id).orElse(null);
    }
}
