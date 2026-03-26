package com.example.orderService.Modal;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
public class OrderDto {
    private long userId;
    private Status status;
    private List<OrderItem> orderItems;
}
