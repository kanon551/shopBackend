package com.example.demo.response;

import com.example.demo.model.Cart;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class CartResponse {

    private Cart cart;
    private String id;
    private String message;
}
