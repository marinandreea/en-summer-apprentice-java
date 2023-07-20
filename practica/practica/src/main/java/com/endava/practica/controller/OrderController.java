package com.endava.practica.controller;

import com.endava.practica.model.Order;
import com.endava.practica.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderServiceImpl;

    @GetMapping("/api/orders")
    public List<Order> getAllOrders(){
        return orderServiceImpl.getOrders();
    }
}
