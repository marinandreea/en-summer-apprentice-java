package com.endava.practica.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VenueDTO venueDTO)) return false;
        return getVenueId() == venueDTO.getVenueId() && getCapacity() == venueDTO.getCapacity() && getLocation().equals(venueDTO.getLocation()) && getType().equals(venueDTO.getType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getVenueId(), getLocation(), getType(), getCapacity());
    }
}
