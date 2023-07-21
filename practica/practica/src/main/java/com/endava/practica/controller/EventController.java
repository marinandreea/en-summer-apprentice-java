package com.endava.practica.controller;

import com.endava.practica.model.Event;
import com.endava.practica.model.TicketCategory;
import com.endava.practica.model.Venue;
import com.endava.practica.model.dto.EventDTO;
import com.endava.practica.model.dto.TicketCategoryDTO;
import com.endava.practica.model.dto.VenueDTO;
import com.endava.practica.service.EventService;
import com.endava.practica.service.TicketCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class EventController {

    @Autowired
    private EventService eventService;
    @Autowired
    private TicketCategoryService ticketCategoryService;
    @Autowired
    private TicketCategoryController ticketCategoryController;

    public VenueDTO venueToDTO(Venue venue) {
        return VenueDTO.builder()
                .venueId(venue.getVenueID())
                .location(venue.getLocation())
                .type(venue.getLocation())
                .capacity(venue.getCapacity()).build();
    }

    public EventDTO eventToDTO(Event event) {
        return EventDTO.builder()
                .eventId(event.getEventID())
                .venue(venueToDTO(event.getVenue()))
                .eventType(event.getEventType().getName())
                .description(event.getDescription())
                .name(event.getName())
                .startDate(event.getStartDate())
                .endDate(event.getEndDate()).build();
    }

    @GetMapping("/getE/{id}")
    public Event getE(@PathVariable int id) {
        return eventService.getEventById(id);
    }

    @GetMapping("/events")
    public List<EventDTO> getEventsByVenueIdAndEventType(@RequestParam(value = "venueId", required = false) String id, @RequestParam(value = "eventType", required = false) String type) {

        List<Event> events = new ArrayList<>();
        if (type == null & id != null) {
            int id2 = Integer.parseInt(id);
            events = eventService.getEventsByVenueId(id2);
        } else if (type != null & id == null) {
            events = eventService.getEventsByEventType(type);
        } else if (id != null & type != null) {
            int id2 = Integer.parseInt(id);
            events = eventService.getEventsByVenueIdAndEventType(id2, type);
        } else {
            events = eventService.getEvents();
        }
        List<EventDTO> eventDTOS = new ArrayList<>();
        for (Event e : events) {
            EventDTO eventDTO = eventToDTO(e);
            List<TicketCategory> ticketCategoriesForEvent = ticketCategoryService.getTicketCategoriesByEventId(e.getEventID());
            List<TicketCategoryDTO> ticketCategoriesForEventToDTO = ticketCategoryController.ticketCategoryListToDTOs(ticketCategoriesForEvent);
            eventDTO.setTicketCategoriesForEvent(ticketCategoriesForEventToDTO);
            eventDTOS.add(eventDTO);
        }
        return eventDTOS;
    }
}
