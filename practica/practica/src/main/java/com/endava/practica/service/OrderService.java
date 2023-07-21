package com.endava.practica.service;

import com.endava.practica.model.Order;
import com.endava.practica.model.Venue;
import com.endava.practica.model.dto.OrderDTO;
import com.endava.practica.repository.OrderRepository;
import com.endava.practica.repository.TicketCategoryRepository;
import com.endava.practica.repository.UserRepository;
import com.endava.practica.repository.VenueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private VenueRepository venueRepository;

    public OrderDTO orderToDTO(Order order) {
        return OrderDTO.builder()
                .eventId(order.getTicketCategory().getEvent().getEventID())
                .timestamp(order.getOrderedAt())
                .ticketCategoryId(order.getTicketCategory().getTicketCategoryID())
                .numberOfTickets(order.getNumberOfTickets())
                .totalPrice(order.getTotalPrice()).build();
    }


    public List<Order> getOrders() {

        List<Order> orders = new ArrayList<>();
        Iterable<Order> orderIterable = orderRepository.findAll();
        orderIterable.forEach(orders::add);
        return orders;
    }

    public String createOrder(Order order) {

        Optional<Venue> venue = venueRepository.findById(order.getTicketCategory().getEvent().getVenue().getVenueID());
        if (venue.isPresent()) {
            int newCapacity = venue.get().getCapacity() - order.getNumberOfTickets();
            if (newCapacity >= 0) {
                venue.get().setCapacity(newCapacity);
                venueRepository.save(venue.get());
                orderRepository.save(order);
                return "order: " + orderToDTO(order);
            } else {
                return "Not enough tickets left! There are only " + venue.get().getCapacity() + " tickets available!";
            }
        }
        return "Order could not be created!";
    }


}
