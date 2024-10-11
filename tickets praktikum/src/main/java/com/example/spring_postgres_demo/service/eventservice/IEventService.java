package com.example.spring_postgres_demo.service.eventservice;

import com.example.spring_postgres_demo.model.Event;
import com.example.spring_postgres_demo.model.Ticket;

import java.util.List;

public interface IEventService {
    void save(Event event) ;

    long[] saveEventsList(List<Event> events) ;

    void update(Event event) ;

    void delete(Event event) ;

    List<Event> findAll() ;

    void deleteAll() ;

    Event findById(long id);

    long findIdByName(String name);

    List<Ticket> findAvailableTickets(Long eventId);

    List<Event> findUpcomingEvents();
}
