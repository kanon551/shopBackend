package com.example.demo.service;

import com.example.demo.model.Cart;
import com.example.demo.repository.CartRepository;
import com.example.demo.response.CartResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    public CartResponse saveCart(Cart cart){
        Cart userIDcart = cartRepository.findByUserID(cart.getUserID());
        CartResponse cartResponse = new CartResponse();
        if(userIDcart != null){
            cartResponse.setMessage("Cart Already Exists with UserID "+cart.getUserID());

            return cartResponse;
        }
        else{
            cartRepository.save(cart);
        }



        cartResponse.setCart(cart);
        cartResponse.setId(cart.get_id());
        cartResponse.setMessage("Added to cart Successfully");

        return cartResponse;
    }

    public CartResponse editCart(Cart cart,Cart existingCart){

        existingCart.setUserID(cart.getUserID());
        existingCart.setProducts(cart.getProducts());

        cartRepository.save(existingCart);

        CartResponse cartResponse = new CartResponse();
        cartResponse.setCart(existingCart);
        cartResponse.setMessage("Updated to cart Successfully");

        return cartResponse;
    }
    public List<Cart> getCarts(){
        return cartRepository.findAll();
    }

    public CartResponse getCartByUserID(String userID){

        Cart withUserID = cartRepository.findByUserID(userID);
        CartResponse cartResponse = new CartResponse();
        if(withUserID == null){
            cartResponse.setMessage("Cant find cart with userID "+userID);
        }
        else {
            cartResponse.setCart(withUserID);
        }
        return cartResponse;

    }

    public List<Cart> getCartByProduct(String name){
        return cartRepository.findByProducts(name);
    }
    public CartResponse deleteCart(String id) {
        cartRepository.deleteById(id);

        CartResponse cartResponse = new CartResponse();
        cartResponse.setId(id);
        cartResponse.setMessage("Cart removed Successfully");

        return cartResponse;


    }


}
