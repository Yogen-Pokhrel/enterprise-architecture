package com.shop.controller;

import com.shop.dto.OrderDto;
import com.shop.dto.ResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody OrderDto order) {
        return ResponseEntity.ok(new ResponseDto("Your order has been created", order));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateOrder(@PathVariable Integer id, @RequestBody OrderDto order) {
        return ResponseEntity.ok(new ResponseDto("Your order has been updated", order));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable Integer id) {
        return ResponseEntity.ok(new ResponseDto("Order has been deleted", id));
    }

    @GetMapping
    public ResponseEntity<?> findAllOrders() {
        return ResponseEntity.ok(new ResponseDto("Your order has been found", new ArrayList<>()));
    }
}
