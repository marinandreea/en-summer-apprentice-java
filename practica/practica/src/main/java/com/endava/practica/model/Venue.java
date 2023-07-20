package com.endava.practica.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name = "venue")
public class Venue implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "venue_id")
    private int venueID;
    @Column
    private String location;
    @Column
    private String type;
    @Column
    private int capacity;

    @Override
    public String toString(){
        return "{\n"
                + "venueID: " + venueID + ",\n"
                + "type: " + type + ",\n"
                + "location: " + location + ",\n"
                + "capacity: " + capacity + "\n}";
    }
}
