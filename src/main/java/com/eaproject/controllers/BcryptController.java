package com.eaproject.controllers;
import com.eaproject.services.BcryptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BcryptController {

    @Autowired
    private BcryptService bcryptService;

    @GetMapping("/hash")
    public String hashPassword() {
        String hashPassword = "testStringPasswordTohash";
        return bcryptService.hashPassword(hashPassword);
    }

    @GetMapping("/hash-multiple")
    public String hashPasswordMultipleTimes() {
        long start = System.currentTimeMillis();
        bcryptService.hashPasswordMultipleTimes("testStringPasswordHashing", 5);
        long end = System.currentTimeMillis();
        return "Hashing " + 5 + " times took " + (end - start) + " ms";
    }
}
