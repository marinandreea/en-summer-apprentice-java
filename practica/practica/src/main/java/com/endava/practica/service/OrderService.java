package com.endava.practica.service;

import com.endava.practica.model.Order;
import com.endava.practica.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<Order> getOrders() {
        return (List<Order>) orderRepository.findAll();
    }
}
