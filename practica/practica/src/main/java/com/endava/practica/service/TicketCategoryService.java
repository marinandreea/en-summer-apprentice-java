package com.endava.practica.service;

import com.endava.practica.model.Event;
import com.endava.practica.model.TicketCategory;
import com.endava.practica.repository.TicketCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

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

        Optional<Event> event = eventService.getEventById(eventId);
        if (event.isPresent()) {
            return ticketCategoryRepository.getAllByEvent(event.get());
        }
        return Collections.emptyList();

    }

    public Optional<TicketCategory> getTicketCategoryById(int id) {
        return ticketCategoryRepository.findById(id);
    }

}
