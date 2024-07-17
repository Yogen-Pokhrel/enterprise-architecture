package com.eaproject.service;

import com.eaproject.controller.DemoController;
import com.eaproject.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class DemoService {

    private static final Logger logger = LoggerFactory.getLogger(DemoService.class);

    @Autowired
    BCryptPasswordEncoder encoder;

    @Async
    public CompletableFuture<String> hashPassword(String password) {
        String psw = encoder.encode(password);
        logger.trace("hash password: {}", psw);
        return CompletableFuture.completedFuture(psw);
    }
}
