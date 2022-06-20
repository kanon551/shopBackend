package com.example.demo.controller;

import com.example.demo.model.Order;
import com.example.demo.repository.OrderRepository;
import com.example.demo.response.OrderResponse;
import com.example.demo.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "https://shopperfront.herokuapp.com")
@RequestMapping("/api/v1")
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

    private static final String AUTH_MECHANISM = "bearerAuth";

    @PostMapping(value = "/order")
    @Operation(security = @SecurityRequirement(name = AUTH_MECHANISM),summary = "Add Order", description = "Save Order", tags = { "Order" })
    public OrderResponse addOrder(@Valid @RequestBody Order order){
        return orderService.saveOrder(order);
    }

    @PutMapping(value = "/order")
    @Operation(security = @SecurityRequirement(name = AUTH_MECHANISM),summary = "Update Order", description = "Edit Order", tags = { "Order" })
    public OrderResponse updateOrder(@Valid @RequestBody Order order){
        try {
            Order existingOrder = orderRepository.findById(order.get_id()).orElse(null);
            return orderService.editOrder(order,existingOrder);
        }
        catch (Exception e){
            OrderResponse orderResponse = new OrderResponse();
            orderResponse.setMessage("No Object available");
            return orderResponse;
        }


    }

    @GetMapping("/order")
    @Operation(security = @SecurityRequirement(name = AUTH_MECHANISM),summary = "Find Order ", description = "Get Order", tags = { "Order" })
    public List<Order> fetchOrder(){
        return orderService.getOrder();
    }

    @GetMapping("/order/{userID}")
    @Operation(security = @SecurityRequirement(name = AUTH_MECHANISM),summary = "Find Order BY UserID ", description = "Get Order by UserID", tags = { "Order" })
    public OrderResponse fetchOrderByUserID(@PathVariable String userID){
        return orderService.getOrderByUserID(userID);
    }

    @GetMapping("/order/product/{name}")
    @Operation(security = @SecurityRequirement(name = AUTH_MECHANISM),summary = "Find Order By Product", description = "Get Order By Product", tags = { "Order" })
    public List<Order> fetchOrderByProduct(@PathVariable String name){
        return orderService.getOrderByProduct(name);
    }


    @DeleteMapping("/order/{id}")
    @Operation(security = @SecurityRequirement(name = AUTH_MECHANISM),summary = "Delete Order", description = "Delete Order By ID", tags = { "Order" })
    public OrderResponse removeOrder(@PathVariable String id ) {
        return orderService.deleteOrder(id);
    }
}
