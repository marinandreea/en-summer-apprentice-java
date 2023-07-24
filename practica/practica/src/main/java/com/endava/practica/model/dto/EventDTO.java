package com.endava.practica.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventDTO {

    private int eventId;
    private VenueDTO venue;
    private String eventType;
    private String description;
    private String name;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private List<TicketCategoryDTO> ticketCategoriesForEvent;

    @Override
    public String toString() {
        return "{\n"
                + "id: " + eventId + ",\n"
                + venue.toString() + ",\n"
                + "type: " + eventType + ",\n"
                + "description: " + description + ",\n"
                + "name: " + name + ",\n"
                + "startDate: " + startDate + ",\n"
                + "endDate: " + endDate + ",\n"
                + ticketCategoriesForEvent.toString() + "\n}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EventDTO eventDTO)) return false;
        return getEventId() == eventDTO.getEventId() && getVenue().equals(eventDTO.getVenue()) && getEventType().equals(eventDTO.getEventType()) && getDescription().equals(eventDTO.getDescription()) && getName().equals(eventDTO.getName()) && getStartDate().equals(eventDTO.getStartDate()) && getEndDate().equals(eventDTO.getEndDate()) && getTicketCategoriesForEvent().equals(eventDTO.getTicketCategoriesForEvent());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEventId(), getVenue(), getEventType(), getDescription(), getName(), getStartDate(), getEndDate(), getTicketCategoriesForEvent());
    }
}
