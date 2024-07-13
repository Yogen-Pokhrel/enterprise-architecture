package com.eaproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DemoController {

    @Autowired
    DemoService demoService;

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
        System.out.println("Hashed password: " + hashed);

        return "You are in right path " + hashed;
    }
}
