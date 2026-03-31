package com.example.cartService.Modal;

import lombok.Data;

@Data
public class CartDto {
    private long userId;
    private long productId;
    private int quantity;
}
