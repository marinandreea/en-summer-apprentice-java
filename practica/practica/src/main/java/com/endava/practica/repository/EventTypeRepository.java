package com.endava.practica.repository;

import com.endava.practica.model.EventType;
import org.springframework.data.repository.CrudRepository;

public interface EventTypeRepository extends CrudRepository<EventType, Integer> {

    EventType getEventTypeByName(String name);
}
