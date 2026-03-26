package com.example.orderService.Modal;

import lombok.Data;

@Data
public class ProductDto {
    private Long productId;
    private String name;
    private double price;
    private int stock;
}
