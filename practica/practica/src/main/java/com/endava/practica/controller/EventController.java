package com.endava.practica.controller;

import com.endava.practica.model.Error;
import com.endava.practica.model.Event;
import com.endava.practica.model.TicketCategory;
import com.endava.practica.model.Venue;
import com.endava.practica.model.dto.EventDTO;
import com.endava.practica.model.dto.TicketCategoryDTO;
import com.endava.practica.model.dto.VenueDTO;
import com.endava.practica.service.EventService;
import com.endava.practica.service.TicketCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public List<EventDTO> getListOfEventDTOS(List<Event> events) {

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

    @GetMapping("/events/{id}")
    public ResponseEntity<?> getEventById(@PathVariable int id) {
        Optional<Event> event = eventService.getEventById(id);
        if (event.isPresent()) {
            return new ResponseEntity<>(event.get(), HttpStatus.OK);
        }
        Error error = new Error("Could not find event with id " + id);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/events")
    public ResponseEntity<?> getEventsByVenueIdAndEventType(@RequestParam(value = "venueId", required = false) Integer id, @RequestParam(value = "eventType", required = false) String type) {

        List<Event> events = new ArrayList<>();
        if (type == null & id != null) {
            events = eventService.getEventsByVenueId(id);
            if (events.isEmpty()) {
                Error error = new Error("No event with venue id " + id + " was found!");
                return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
            }
        } else if (type != null & id == null) {
            events = eventService.getEventsByEventType(type);
            if (events.isEmpty()) {
                Error error = new Error("No event with event type " + type + " was found!");
                return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
            }
        } else if (id != null & type != null) {
            events = eventService.getEventsByVenueIdAndEventType(id, type);
            if (events.isEmpty()) {
                Error error = new Error("No event with venue id " + id + " and event type " + type + " was found!");
                return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
            }
        } else {
            events = eventService.getEvents();
        }
        return new ResponseEntity<>(getListOfEventDTOS(events), HttpStatus.OK);
    }
}
