package com.example.cartService.Dao;

import com.example.cartService.Modal.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepo extends JpaRepository<Cart,Long> {
    Cart findByUserId(long userId);
}
