package com.endava.practica.controller;
import com.endava.practica.model.Event;
import com.endava.practica.model.dto.EventDTO;
import com.endava.practica.model.dto.TicketCategoryDTO;
import com.endava.practica.service.EventService;
import com.endava.practica.service.TicketCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class EventController {

    @Autowired
    private EventService eventService;
    @Autowired
    private TicketCategoryService ticketCategoryService;

    @GetMapping("/api/getEventsByVenueIdAndEventType/{venueId}/{eventType}")
    public List<EventDTO> getEventsByVenueIdAndEventType(@PathVariable int venueId, @PathVariable String eventType) {
        List<Event> eventsWithVenueIdAndEventType = eventService.getEventsByVenueIdAndEventType(venueId,eventType);
        List<EventDTO> eventDTOS = new ArrayList<>();
        for(Event e:eventsWithVenueIdAndEventType){
            EventDTO eventDTO = eventService.eventToDTO(e);
            List<TicketCategoryDTO> ticketCategoriesForEventDTO = ticketCategoryService.getTicketCategoriesByEventId(e.getEventID());
            eventDTO.setTicketCategoriesForEvent(ticketCategoriesForEventDTO);
            eventDTOS.add(eventDTO);
        }
        return eventDTOS;
    }
}
