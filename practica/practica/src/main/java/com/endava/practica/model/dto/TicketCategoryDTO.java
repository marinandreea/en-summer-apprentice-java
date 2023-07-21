package com.endava.practica.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}
