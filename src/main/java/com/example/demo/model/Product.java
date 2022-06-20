package com.example.demo.model;

import lombok.Data;
import org.bson.types.Binary;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Data
@Component
@Document(collection = "products")
public class Product {

    private String _id;
    private String title;
    private String desc;
    private Binary img;
    private ArrayList<String> categories;
    private ArrayList<String> size;
    private ArrayList<String> color;
    private String price;
    private boolean inStock;

    private String createdDate;
    private String updatedDate;

}
