package com.example.cartService.Controller;

import com.example.cartService.Modal.CartDto;
import com.example.cartService.Service.CartService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("cart")
public class CartController {

    @Autowired
    CartService cartService;

    @PostMapping("addToCart")
    public ResponseEntity<String> addToCart(@RequestBody CartDto cartDto)
    {
        return new ResponseEntity<>(cartService.addToCart(cartDto), HttpStatus.CREATED);
    }

}
