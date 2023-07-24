package com.endava.practica.controller;

import com.endava.practica.model.Venue;
import com.endava.practica.service.VenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class VenueController {

    @Autowired
    private VenueService venueService;

    @GetMapping("/venues")
    public List<Venue> getAllVenues() {
        return venueService.getVenues();
    }
}
