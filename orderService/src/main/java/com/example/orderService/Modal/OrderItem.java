package com.example.orderService.Modal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long itemId;
    private long productId;
    private int quantity;
    private double price;
    private String productName;
    @ManyToOne
    @JoinColumn(name="orderId")
    @JsonIgnore
    private Order order;
}
