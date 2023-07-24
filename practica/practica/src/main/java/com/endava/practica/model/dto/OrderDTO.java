package com.endava.practica.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Objects;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {

    private int eventId;
    private LocalDateTime timestamp;
    private int ticketCategoryId;
    private int numberOfTickets;
    private double totalPrice;

    @Override
    public String toString() {
        return "{\n"
                + "eventId: " + eventId + ",\n"
                + "timestamp: " + timestamp + ",\n"
                + "ticketCategoryId: " + ticketCategoryId + ",\n"
                + "numberOfTickets: " + numberOfTickets + ",\n"
                + "totalPrice: " + totalPrice + "\n}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderDTO orderDTO)) return false;
        return getEventId() == orderDTO.getEventId() && getTicketCategoryId() == orderDTO.getTicketCategoryId() && getNumberOfTickets() == orderDTO.getNumberOfTickets() && Double.compare(orderDTO.getTotalPrice(), getTotalPrice()) == 0 && getTimestamp().equals(orderDTO.getTimestamp());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEventId(), getTimestamp(), getTicketCategoryId(), getNumberOfTickets(), getTotalPrice());
    }
}
