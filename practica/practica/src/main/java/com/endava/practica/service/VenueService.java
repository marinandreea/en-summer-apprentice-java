package com.endava.practica.service;

import com.endava.practica.model.Order;
import com.endava.practica.model.Venue;
import com.endava.practica.repository.VenueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VenueService {

    @Autowired
    private VenueRepository venueRepository;

    public List<Venue> getVenues() {
        List<Venue> venues = new ArrayList<>();
        Iterable<Venue> venueIterable = venueRepository.findAll();
        venueIterable.forEach(venues::add);
        return venues;
    }

    public Optional<Venue> getVenueById(int venueId) {
        return venueRepository.findById(venueId);
    }
}
