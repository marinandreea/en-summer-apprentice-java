package com.endava.practica.service;


import com.endava.practica.model.Event;
import com.endava.practica.model.EventType;
import com.endava.practica.model.Venue;
import com.endava.practica.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private VenueService venueService;
    @Autowired
    private EventTypeService eventTypeService;

    public Optional<Event> getEventById(int eventId) {
        return eventRepository.findById(eventId);

    }

    public List<Event> getEvents() {
        List<Event> events = new ArrayList<>();
        Iterable<Event> eventIterable = eventRepository.findAll();
        eventIterable.forEach(events::add);
        return events;
    }

    public List<Event> getEventsByVenueId(int venueId) {
        Optional<Venue> venue = venueService.getVenueById(venueId);
        if (venue.isPresent()) {
            return eventRepository.findAllByVenue(venue.get());
        }
        return Collections.emptyList();
    }

    public List<Event> getEventsByEventType(String type) {
        EventType eventType = eventTypeService.getEventTypeByType(type);
        return eventRepository.findAllByEventType(eventType);
    }


    public List<Event> getEventsByVenueIdAndEventType(int venueId, String type) {

        Optional<Venue> venue = venueService.getVenueById(venueId);
        if (venue.isPresent()) {
            EventType eventType = eventTypeService.getEventTypeByType(type);
            return eventRepository.findAllByVenueAndEventType(venue.get(), eventType);
        }

        return Collections.emptyList();
    }

}
