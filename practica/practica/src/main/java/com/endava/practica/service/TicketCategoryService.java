package com.endava.practica.service;

import com.endava.practica.model.Order;
import com.endava.practica.model.TicketCategory;
import com.endava.practica.model.dto.TicketCategoryDTO;
import com.endava.practica.repository.TicketCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketCategoryService {

    @Autowired
    private TicketCategoryRepository ticketCategoryRepository;
    @Autowired
    private EventService eventService;

    public List<TicketCategory> getTicketCategories() {
        List<TicketCategory> ticketCategories = new ArrayList<>();
        Iterable<TicketCategory> ticketCategoryIterable = ticketCategoryRepository.findAll();
        ticketCategoryIterable.forEach(ticketCategories::add);
        return ticketCategories;
    }

    public List<TicketCategory> getTicketCategoriesByEventId(int eventId) {
        return ticketCategoryRepository.getAllByEvent(eventService.getEventById(eventId));
    }

}
