package com.example.demo.response;

import com.example.demo.model.Product;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class ProductResponse {

    private Product product;
    private String id;
    private String message;
}
