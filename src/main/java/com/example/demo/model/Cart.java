package com.example.demo.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Data
@Component
@Document(collection = "cart")
public class Cart {

    private String _id;
    private String userID;
    private ArrayList<String> products;
}
