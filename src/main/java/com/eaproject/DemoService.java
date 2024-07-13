package com.eaproject;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class DemoService {

    @Async
    public CompletableFuture<String> hashPassword(String password) {
        return CompletableFuture.completedFuture("Hi I am resolved");
    }
}
