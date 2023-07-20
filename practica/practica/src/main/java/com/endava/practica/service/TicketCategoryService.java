package com.endava.practica.service;

import com.endava.practica.model.TicketCategory;
import com.endava.practica.model.dto.TicketCategoryDTO;
import com.endava.practica.repository.TicketCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketCategoryService {

    @Autowired
    private TicketCategoryRepository ticketCategoryRepository;

    @Autowired
    private EventService eventService;

    public static TicketCategoryDTO fromEntity(TicketCategory ticketCategory){
        return TicketCategoryDTO.builder()
                .ticketCategoryId(ticketCategory.getTicketCategoryID())
                .description(ticketCategory.getDescription())
                .price(ticketCategory.getPrice()).build();
    }

    public List<TicketCategory> getTicketCategories() {
        return (List<TicketCategory>) ticketCategoryRepository.findAll();
    }

    public List<TicketCategoryDTO> getTicketCategoriesByEventId(int eventId){
        List<TicketCategory> ticketCategories = ticketCategoryRepository.getAllByEvent(eventService.getEventById(eventId));
        return ticketCategories.stream().map(TicketCategoryService::fromEntity).collect(Collectors.toList());
    }

}
