package com.example.demo.repository;
import com.example.demo.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OrderRepository extends MongoRepository<Order,String> {

    List<Order> findByProducts(String name);
    Order findByUserID(String userID);
}
