package com.example.demo.repository;

import com.example.demo.model.Cart;
import com.example.demo.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CartRepository extends MongoRepository<Cart,String> {
    List<Cart> findByProducts(String name);
    Cart findByUserID(String userID);

}
