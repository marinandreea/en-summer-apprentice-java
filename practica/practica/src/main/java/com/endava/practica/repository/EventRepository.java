package com.endava.practica.repository;

import com.endava.practica.model.Event;
import com.endava.practica.model.EventType;
import com.endava.practica.model.Venue;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends CrudRepository<Event,Integer> {

    List<Event> findAllByVenueAndEventType(Venue venue, EventType eventType);
}
