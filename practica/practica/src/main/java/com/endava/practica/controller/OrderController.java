package com.endava.practica.controller;

import com.endava.practica.model.Error;
import com.endava.practica.model.Order;
import com.endava.practica.model.TicketCategory;
import com.endava.practica.model.User;
import com.endava.practica.model.dto.OrderDTO;
import com.endava.practica.service.OrderService;
import com.endava.practica.service.TicketCategoryService;
import com.endava.practica.service.UserService;
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
    private UserService userService;
    @Autowired
    private TicketCategoryService ticketCategoryService;


    public Order orderDTOToOrder(OrderDTO orderDTO) {
        Optional<User> user = userService.getUserById(1);
        if (user.isPresent()) {
            Optional<TicketCategory> ticketCategory = ticketCategoryService.getTicketCategoryById(orderDTO.getTicketCategoryId());
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

        return new Order(0);
    }

    public OrderDTO orderToDTO(Order order) {
        return OrderDTO.builder()
                .eventId(order.getTicketCategory().getEvent().getEventID())
                .timestamp(order.getOrderedAt())
                .ticketCategoryId(order.getTicketCategory().getTicketCategoryID())
                .numberOfTickets(order.getNumberOfTickets())
                .totalPrice(order.getTotalPrice()).build();
    }

    @GetMapping("/allOrders")
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
        Optional<OrderDTO> orderDTO = Optional.of(new OrderDTO());
        if (orderFromDTO.getOrderID() != 0) {
            orderDTO = orderService.createOrder(orderFromDTO, orderToDTO(orderFromDTO));
            if (orderDTO.isEmpty()) {
                Error error = new Error("There are not enough tickets left!");
                return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            Error error = new Error("Could not create order with this ticket category id!");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("order:" + orderDTO.get(), HttpStatus.OK);

    }
}
