package com.example.cartService.Modal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class CartItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long cartItemId;
    private long productId;
    @ManyToOne
    @JoinColumn(name="cartId")
    @JsonIgnore
    private Cart cart;
    private int quantity;
}
