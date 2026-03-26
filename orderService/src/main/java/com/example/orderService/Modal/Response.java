package com.example.orderService.Modal;

import lombok.Data;

import java.util.List;

@Data
public class Response {

    private long orderId;

    private Status status;

    private double totalPrice;

}
