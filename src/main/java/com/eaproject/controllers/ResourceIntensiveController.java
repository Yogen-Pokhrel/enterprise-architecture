package com.eaproject.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResourceIntensiveController {
    @GetMapping("/consume-resources")
    public String consumeResources() {
        // Simulate a resource-intensive task
        consumeCPU();
        return "Resource consumption initiated";
    }

    private void consumeCPU() {
        // Simulate CPU-intensive task
        System.out.println("Finding the fibonacci");
        long fibonacciResult = fibonacci(10);
        System.out.println("Fibonacci result: " + fibonacciResult);
    }

    private long fibonacci(int n) {
        if (n <= 1) {
            return n;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }
}
