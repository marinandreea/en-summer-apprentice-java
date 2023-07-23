package com.endava.practica.controller;

import com.endava.practica.model.Order;
import com.endava.practica.model.TicketCategory;
import com.endava.practica.model.User;
import com.endava.practica.model.dto.OrderDTO;
import com.endava.practica.repository.TicketCategoryRepository;
import com.endava.practica.repository.UserRepository;
import com.endava.practica.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
            Optional<TicketCategory> ticketCategory = ticketCategoryRepository.findById(orderDTO.getTicketCategoryId());
            if (ticketCategory.isPresent()) {
                Double totalPrice = orderDTO.getNumberOfTickets() * ticketCategory.get().getPrice();

                return Order.builder()
                        .user(user.get())
                        .ticketCategory(ticketCategory.get())
                        .orderedAt(LocalDateTime.now())
                        .numberOfTickets(orderDTO.getNumberOfTickets())
                        .totalPrice(totalPrice).build();
            }

        }

        return new Order(50000);
    }

    public OrderDTO orderToDTO(Order order) {
        return OrderDTO.builder()
                .eventId(order.getTicketCategory().getEvent().getEventID())
                .timestamp(order.getOrderedAt())
                .ticketCategoryId(order.getTicketCategory().getTicketCategoryID())
                .numberOfTickets(order.getNumberOfTickets())
                .totalPrice(order.getTotalPrice()).build();
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
            OrderDTO orderDTO = orderToDTO(o);
            orderDTOList.add(orderDTO);
        }
        return orderDTOList;
    }

    @PostMapping("/createOrder")
    public ResponseEntity<?> addNewOrder(@RequestBody OrderDTO newOrder) {

        Order orderFromDTO = orderDTOToOrder(newOrder);
        if (orderFromDTO.getOrderID() != 50000) {
            String response = orderService.createOrder(orderFromDTO, orderToDTO(orderFromDTO));
            if (response.contains("Error")) {
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {

            return new ResponseEntity<>("Could not create order with this ticket category id!", HttpStatus.NOT_FOUND);
        }

    }
}
