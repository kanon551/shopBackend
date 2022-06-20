package com.example.demo.response;

import com.example.demo.model.Order;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class OrderResponse {
    private Order order;
    private String message;
}
