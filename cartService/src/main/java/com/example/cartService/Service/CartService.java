package com.example.cartService.Service;

import com.example.cartService.Dao.CartRepo;
import com.example.cartService.Modal.Cart;
import com.example.cartService.Modal.CartDto;
import com.example.cartService.Modal.CartItems;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class CartService {

    @Autowired
    CartRepo cartRepo;

    public String addToCart(CartDto cartDto) {

        Cart cart=cartRepo.findByUserId(cartDto.getUserId());
        if(cart!=null)
        {
            List<CartItems> cartItems=cart.getCartItems();
            boolean newProduct=false;
            for(CartItems cartItem:cartItems)
            {
                if(Objects.equals(cartDto.getProductId(),cartItem.getProductId()))
                {
                    cartItem.setQuantity(cartItem.getQuantity()+cartDto.getQuantity());
                }
                else {
                    newProduct=true;
                    break;
                }
            }
            if(newProduct)
            {
                CartItems newItem=new CartItems();
                newItem.setQuantity(cartDto.getQuantity());
                newItem.setProductId(cartDto.getProductId());
                newItem.setCart(cart);
                cartItems.add(newItem);
            }
            cartRepo.save(cart);
        }
        else {
            Cart newCart = new Cart();
            newCart.setUserId(cartDto.getUserId());
            CartItems cartItem= new CartItems();
            cartItem.setQuantity(cartDto.getQuantity());
            cartItem.setProductId(cartDto.getProductId());
            cartItem.setCart(newCart);
            ArrayList<CartItems> cartItems=new ArrayList<>();
            cartItems.add(cartItem);
            newCart.setCartItems(cartItems);

            cartRepo.save(newCart);
        }
        return "Added";
    }
}
