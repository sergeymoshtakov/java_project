package com.example.spring_postgres_demo.service.ticketservice;

import com.example.spring_postgres_demo.model.Ticket;

import java.util.List;

public interface ITicketService {
    void save(Ticket ticket) ;

    long[] saveTicketsList(List<Ticket> tickets) ;

    void update(Ticket ticket) ;

    void delete(Ticket ticket) ;

    List<Ticket> findAll() ;

    void deleteAll() ;

    Ticket findById(long id);
}
