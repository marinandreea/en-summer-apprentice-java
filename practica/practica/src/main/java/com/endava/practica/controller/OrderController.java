package com.endava.practica.controller;

import com.endava.practica.model.Order;
import com.endava.practica.model.TicketCategory;
import com.endava.practica.model.User;
import com.endava.practica.model.dto.OrderDTO;
import com.endava.practica.repository.TicketCategoryRepository;
import com.endava.practica.repository.UserRepository;
import com.endava.practica.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TicketCategoryRepository ticketCategoryRepository;


    public Order orderDTOToOrder(OrderDTO orderDTO) {
        Optional<User> user = userRepository.findById(1);
        if (user.isPresent()) {
            TicketCategory ticketCategory = ticketCategoryRepository.findById(orderDTO.getTicketCategoryId()).get();
            Double totalPrice = orderDTO.getNumberOfTickets() * ticketCategory.getPrice();

            return Order.builder()
                    .user(user.get())
                    .ticketCategory(ticketCategory)
                    .orderedAt(LocalDateTime.now())
                    .numberOfTickets(orderDTO.getNumberOfTickets())
                    .totalPrice(totalPrice).build();
        }

        return new Order();
    }

    @GetMapping("/api/orders")
    public List<Order> getAllOrders() {
        return orderService.getOrders();
    }

    @GetMapping("/orders")
    public List<OrderDTO> getOrders() {
        List<Order> orders = orderService.getOrders();
        List<OrderDTO> orderDTOList = new ArrayList<>();
        for (Order o : orders) {
            OrderDTO orderDTO = orderService.orderToDTO(o);
            orderDTOList.add(orderDTO);
        }
        return orderDTOList;
    }

    @PostMapping("/createOrder")
    public String addNewOrder(@RequestBody OrderDTO newOrder) {

        Order orderFromDTO = orderDTOToOrder(newOrder);
        return orderService.createOrder(orderFromDTO);

    }
}
