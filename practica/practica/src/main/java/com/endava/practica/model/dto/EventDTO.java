package com.endava.practica.model.dto;

import com.endava.practica.model.Venue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

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

}
