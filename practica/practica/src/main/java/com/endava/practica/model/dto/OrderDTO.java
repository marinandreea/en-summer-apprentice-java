package com.endava.practica.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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


}
