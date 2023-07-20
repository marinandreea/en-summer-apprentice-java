package com.endava.practica.service;

import com.endava.practica.model.Event;
import com.endava.practica.model.EventType;
import com.endava.practica.model.Venue;
import com.endava.practica.model.dto.EventDTO;
import com.endava.practica.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private VenueService venueService;
    @Autowired
    private EventTypeService eventTypeService;

    public  EventDTO eventToDTO(Event event){
        return EventDTO.builder()
                .eventId(event.getEventID())
                .venue(event.getVenue())
                .eventType(event.getEventType().getName())
                .description(event.getDescription())
                .name(event.getName())
                .startDate(event.getStartDate())
                .endDate(event.getEndDate()).build();
    }

    public Event getEventById(int eventId){
        return eventRepository.findById(eventId).get();
    }

    public List<Event> getEventsByVenueIdAndEventType(int venueId, String type) {

        Venue venue = venueService.getVenueById(venueId).get();
        EventType eventType = eventTypeService.getEventTypeByType(type);

        if(venue != null && eventType != null){
            return eventRepository.findAllByVenueAndEventType(venue,eventType);
        }
        return  null;
    }

}
