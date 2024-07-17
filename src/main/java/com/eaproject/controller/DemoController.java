package com.eaproject.controller;

import com.eaproject.configuration.CustomException;
import com.eaproject.dto.SampleDto;
import com.eaproject.service.DemoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class DemoController {

    @Autowired
    DemoService demoService;

    private static final Logger logger = LoggerFactory.getLogger(DemoController.class);

    @GetMapping
    public String getMessage() throws Exception {
        String password = "sfsdfsdf73284384345345dsfdsf";
        demoService.hashPassword(password);

        password = "asdsdf77sdfsdfsdfsdasdsdf77sdfsdfsdfsdasdsdf77sdfsdfsdfsdasdsdf77sdfsdfsdfsd";
        demoService.hashPassword(password);

        password = "BCrypt: The BCrypt.hashpw() method is used to hash the password. BCrypt.gensalt() generates a salt for the hashing process.\n";
        demoService.hashPassword(password);
        password = "asdsdf77sdfsdfsdfsdasdsdf77sdfsdfsdfsdasdsdf77sdfsdfsdfsdasdsdf77sdfsdfsdfsd";
        demoService.hashPassword(password);

        password = "BCrypt: The BCrypt.hashpw() method is used to hash the password. BCrypt.gensalt() generates a salt for the hashing process.\n";
        demoService.hashPassword(password);

        password = "asdsdf77sdfsdfsdfsdasdsdf77sdfsdfsdfsdasdsdf77sdfsdfsdfsdasdsdf77sdfsdfsdfsd";
        demoService.hashPassword(password);

        password = "sdfsfsdf sdfsdfsfs dfs ffo46457567jdffdglfdgdfg dfg dfg dg dfg dgdf dfg df gfdg fdfgfdg\n";
        String hashed = demoService.hashPassword(password).get();

        return "You are in right path";
    }

    @PostMapping("/generateError")
    public String generateError(@RequestBody SampleDto sampleDto) throws Exception {
        if(sampleDto.getQuantity() < 1){
            throw new CustomException("Quantity must be at least 1", sampleDto.toString());
        }
        return "No error occurred";
    }

    @PostMapping("/generateWarn")
    public String generateWarning() {
        logger.warn("Warning logged");
        return "Warning logged";
    }

    @PostMapping("/generateInfo")
    public String generateInfo() {
        logger.info("Info logged");
        return "Info logged";
    }
}
