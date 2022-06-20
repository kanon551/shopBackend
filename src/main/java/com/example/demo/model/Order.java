package com.example.demo.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Data
@Component
@Document(collection = "order")
public class Order {

    private String _id;
    private String userID;
    private ArrayList<ProductList> products;
    private double amount;
    private Address address;
    private String status;

    private String createdTime;
    private String updatedTime;
}
