package com.shop.controller;

import com.shop.dto.ResponseDto;
import com.shop.dto.ShopDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/shop")
public class ShopController {
    @PostMapping
    public ResponseEntity<?> createShop(@RequestBody ShopDto shop) {
        return ResponseEntity.ok(new ResponseDto("Your shop has been created", shop));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateShop(@PathVariable Integer id, @RequestBody ShopDto shop) {
        return ResponseEntity.ok(new ResponseDto("Your shop has been updated", shop));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteShop(@PathVariable Integer id) {
        return ResponseEntity.ok(new ResponseDto("Shop has been deleted", id));
    }

    @GetMapping
    public ResponseEntity<?> findAllShops() {
        return ResponseEntity.ok(new ResponseDto("Your shop has been found", new ArrayList<>()));
    }
}
