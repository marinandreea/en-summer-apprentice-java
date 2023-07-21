package com.endava.practica.controller;

import com.endava.practica.model.TicketCategory;
import com.endava.practica.model.dto.TicketCategoryDTO;
import com.endava.practica.service.TicketCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TicketCategoryController {

    @Autowired
    private TicketCategoryService ticketCategoryService;

    public TicketCategoryDTO fromEntity(TicketCategory ticketCategory) {
        return TicketCategoryDTO.builder()
                .ticketCategoryId(ticketCategory.getTicketCategoryID())
                .description(ticketCategory.getDescription())
                .price(ticketCategory.getPrice()).build();
    }

    public List<TicketCategoryDTO> ticketCategoryListToDTOs(List<TicketCategory> ticketCategoryList) {
        List<TicketCategoryDTO> ticketCategoryDTOList = new ArrayList<>();
        for (TicketCategory ticketCategory : ticketCategoryList) {
            TicketCategoryDTO ticketCategoryDTO = fromEntity(ticketCategory);
            ticketCategoryDTOList.add(ticketCategoryDTO);
        }
        return ticketCategoryDTOList;
    }

    @GetMapping("/api/ticketCategories")
    public List<TicketCategory> getAllTicketCategories() {
        return ticketCategoryService.getTicketCategories();
    }

    @GetMapping("/api/ticketCategories/{eventId}")
    public List<TicketCategoryDTO> getAllTicketCategoriesByEventId(@PathVariable int eventId) {
        List<TicketCategoryDTO> ticketCategoryDTOList = new ArrayList<>();
        List<TicketCategory> ticketCategoryList = ticketCategoryService.getTicketCategoriesByEventId(eventId);
        for (TicketCategory ticketCategory : ticketCategoryList) {
            TicketCategoryDTO ticketCategoryDTO = fromEntity(ticketCategory);
            ticketCategoryDTOList.add(ticketCategoryDTO);
        }
        return ticketCategoryDTOList;
    }
}
