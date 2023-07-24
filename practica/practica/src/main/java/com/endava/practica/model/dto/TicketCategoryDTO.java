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
public class TicketCategoryDTO {

    private int ticketCategoryId;
    private String description;
    private double price;

    @Override
    public String toString() {
        return "{\n" +
                "id: " + ticketCategoryId + "\n" +
                "description: " + description + "\n" +
                "price: " + price + "\n}\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TicketCategoryDTO that)) return false;
        return getTicketCategoryId() == that.getTicketCategoryId() && Double.compare(that.getPrice(), getPrice()) == 0 && getDescription().equals(that.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTicketCategoryId(), getDescription(), getPrice());
    }
}
