package com.shop.dto;

import lombok.Data;

@Data
public class OrderDto {
    private String productName;
    private String productPrice;
    private Integer quantity;
}
