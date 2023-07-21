package com.endava.practica.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VenueDTO {

    private int venueId;
    private String location;
    private String type;
    private int capacity;

    @Override
    public String toString() {
        return "{\n"
                + "venueID: " + venueId + ",\n"
                + "type: " + type + ",\n"
                + "location: " + location + ",\n"
                + "capacity: " + capacity + "\n}";
    }
}
