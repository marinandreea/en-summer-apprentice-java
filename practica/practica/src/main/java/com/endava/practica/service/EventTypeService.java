package com.endava.practica.service;

import com.endava.practica.model.EventType;
import com.endava.practica.repository.EventTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventTypeService {

    @Autowired
    private EventTypeRepository eventTypeRepository;

    public EventType getEventTypeByType(String name) {
        return eventTypeRepository.getEventTypeByName(name);
    }
}
