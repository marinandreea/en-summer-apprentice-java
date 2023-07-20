package com.endava.practica.controller;

import com.endava.practica.model.TicketCategory;
import com.endava.practica.model.dto.TicketCategoryDTO;
import com.endava.practica.service.TicketCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TicketCategoryController {

    @Autowired
    private TicketCategoryService ticketCategoryService;

    @GetMapping("/api/ticketCategories")
    public List<TicketCategory> getAllTicketCategories(){
        return ticketCategoryService.getTicketCategories();
    }

    @GetMapping("/api/ticketCategories/{eventId}")
    public List<TicketCategoryDTO> getAllTicketCategoriesByEventId(@PathVariable int eventId){
        return ticketCategoryService.getTicketCategoriesByEventId(eventId);
    }
}
