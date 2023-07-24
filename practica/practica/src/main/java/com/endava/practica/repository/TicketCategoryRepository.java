package com.endava.practica.repository;

import com.endava.practica.model.Event;
import com.endava.practica.model.TicketCategory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketCategoryRepository extends CrudRepository<TicketCategory, Integer> {

    List<TicketCategory> getAllByEvent(Event event);
}
