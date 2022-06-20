package com.example.demo.service;


import com.example.demo.model.Order;
import com.example.demo.repository.OrderRepository;
import com.example.demo.response.OrderResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public OrderResponse saveOrder(Order order) {
        OrderResponse orderResponse = new OrderResponse();
        Date date = new Date();
        order.setCreatedTime(date.toString());
        order.setUpdatedTime(date.toString());

        orderRepository.save(order);
        orderResponse.setOrder(order);
        orderResponse.setMessage("Order saved Successfully");

        return orderResponse;

    }
    public OrderResponse editOrder(Order order,Order existingOrder){

        Date date = new Date();

        existingOrder.setUserID(order.getUserID());
        existingOrder.setAmount(order.getAmount());
        existingOrder.setAddress(order.getAddress());
        existingOrder.setStatus(order.getStatus());
        existingOrder.setProducts(order.getProducts());
        existingOrder.setUpdatedTime(date.toString());

        orderRepository.save(existingOrder);

        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setOrder(existingOrder);
        orderResponse.setMessage("Order Updated Successfully");

        return orderResponse;
    }

    public List<Order> getOrder(){
        return orderRepository.findAll();
    }

    public OrderResponse getOrderByUserID(String userID){

        Order withUserID = orderRepository.findByUserID(userID);
        OrderResponse orderResponse = new OrderResponse();
        if(withUserID == null){
            orderResponse.setMessage("Cant find Order with userID "+userID);
        }
        else {
            orderResponse.setOrder(withUserID);
        }
        return orderResponse;

    }

    public List<Order> getOrderByProduct(String name){
        return orderRepository.findByProducts(name);
    }

    public OrderResponse deleteOrder(String id) {
        orderRepository.deleteById(id);

        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setMessage("Cart removed Successfully");

        return orderResponse;


    }

}
