package com.example.demo.controller;

import com.example.demo.model.Cart;
import com.example.demo.repository.CartRepository;
import com.example.demo.response.CartResponse;
import com.example.demo.service.CartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1")
@RestController

public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private CartRepository cartRepository;

    private static final String AUTH_MECHANISM = "bearerAuth";

    @PostMapping(value = "/cart")
    @Operation(security = @SecurityRequirement(name = AUTH_MECHANISM),summary = "Add Cart", description = "Save Cart", tags = { "Cart" })
    public CartResponse addCart(@Valid @RequestBody Cart cart){
        return cartService.saveCart(cart);
    }

    @PutMapping(value = "/cart")
    @Operation(security = @SecurityRequirement(name = AUTH_MECHANISM),summary = "Update Cart", description = "Edit Cart", tags = { "Cart" })
    public CartResponse updateCart(@Valid @RequestBody Cart cart){
        Cart existingCart = cartRepository.findById(cart.get_id()).orElse(null);
        return cartService.editCart(cart,existingCart);
    }

    @GetMapping("/cart")
    @Operation(security = @SecurityRequirement(name = AUTH_MECHANISM),summary = "Find Cart ", description = "Get Cart", tags = { "Cart" })
    public List<Cart> fetchCarts(){
        return cartService.getCarts();
    }

    @GetMapping("/cart/{userID}")
    @Operation(security = @SecurityRequirement(name = AUTH_MECHANISM),summary = "Find Cart BY UserID ", description = "Get Cart by UserID", tags = { "Cart" })
    public CartResponse fetchCartByUserID(@PathVariable String userID){
        return cartService.getCartByUserID(userID);
    }

    @GetMapping("/cart/product/{name}")
    @Operation(security = @SecurityRequirement(name = AUTH_MECHANISM),summary = "Find Cart By Product", description = "Get Cart By Product", tags = { "Cart" })
    public List<Cart> fetchCartsByProduct(@PathVariable String name){
        return cartService.getCartByProduct(name);
    }

    @DeleteMapping("/cart/{id}")
    @Operation(security = @SecurityRequirement(name = AUTH_MECHANISM),summary = "Delete Cart", description = "Delete Cart By ID", tags = { "Cart" })
    public CartResponse removeCart(@PathVariable String id ) {
        return cartService.deleteCart(id);
    }




}
