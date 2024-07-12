package com.shop.controller;

import com.shop.dto.PaymentDto;
import com.shop.dto.ResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@RestController
@RequestMapping("/payments")
public class PaymentController {
    @PostMapping
    public ResponseEntity<?> createPayment(@RequestBody PaymentDto payment) {
        return ResponseEntity.ok(new ResponseDto("Your payment has been created", payment));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePayment(@PathVariable Integer id, @RequestBody PaymentDto payment) {
        return ResponseEntity.ok(new ResponseDto("Your payment has been updated", payment));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePayment(@PathVariable Integer id) {
        return ResponseEntity.ok(new ResponseDto("Payment has been deleted", id));
    }

    @GetMapping
    public ResponseEntity<?> findAllPayments() {
        return ResponseEntity.ok(new ResponseDto("Your payment has been found", new ArrayList<>()));
    }
}
