package com.eaproject.services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class BcryptService {

    private final BCryptPasswordEncoder passwordEncoder;

    public BcryptService() {
        this.passwordEncoder = new BCryptPasswordEncoder(12); // 12 is the strength, higher values are more CPU-intensive
    }

    public String hashPassword(String password) {
        return passwordEncoder.encode(password);
    }

    public void hashPasswordMultipleTimes(String password, int times) {
        for (int i = 0; i < times; i++) {
            passwordEncoder.encode(password);
        }
    }
}
