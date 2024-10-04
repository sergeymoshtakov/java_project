package com.example.spring_postgres_demo.util;

import com.example.spring_postgres_demo.enums.Statuses;
import com.example.spring_postgres_demo.factory.CustomerFactory;
import com.example.spring_postgres_demo.factory.EventFactory;
import com.example.spring_postgres_demo.factory.PlaceFactory;
import com.example.spring_postgres_demo.factory.StatusFactory;
import com.example.spring_postgres_demo.factory.TicketFactory;
import com.example.spring_postgres_demo.model.*;
import com.example.spring_postgres_demo.service.customerservice.CustomerService;
import com.example.spring_postgres_demo.service.eventservice.EventService;
import com.example.spring_postgres_demo.service.placeservice.PlaceService;
import com.example.spring_postgres_demo.service.statusservice.StatusService;
import com.example.spring_postgres_demo.service.ticketservice.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketServiceDBInitializer {
    @Autowired
    private TicketService ticketService;
    @Autowired
    private StatusService statusService;
    @Autowired
    private EventService eventService;
    @Autowired
    private PlaceService placeService;
    @Autowired
    private CustomerService customerService;

    @Autowired
    private StatusFactory statusFactory;
    @Autowired
    private EventFactory eventFactory;
    @Autowired
    private PlaceFactory placeFactory;
    @Autowired
    private CustomerFactory customerFactory;
    @Autowired
    private TicketFactory ticketFactory;

    public void cleanAll(){
        ticketService.deleteAll();
        eventService.deleteAll();
        placeService.deleteAll();
        customerService.deleteAll();
        statusService.deleteAll();
    }

    public void initializeStatuses() {
        for (Statuses status : Statuses.values()) {
            Status statusEntity = new Status();
            statusEntity.setId(status.getId());
            statusEntity.setName(status.getName());
            statusService.save(statusEntity);
        }
    }

    public void initializeEvents(int count) {
        for (int i = 0; i < count; i++) {
            Event event = eventFactory.getRandomElement();
            eventService.save(event);
        }
    }

    public void initializePlaces(int count) {
        for (int i = 0; i < count; i++) {
            Place place = placeFactory.getRandomElement();
            placeService.save(place);
        }
    }

    public void initializeCustomers(int count) {
        for (int i = 0; i < count; i++) {
            Customer customer = customerFactory.getRandomElement();
            customerService.save(customer);
        }
    }

    public void initializeTickets(int count) {
        for (int i = 0; i < count; i++) {
            Ticket ticket = ticketFactory.getRandomElement();
            ticketService.save(ticket);
        }
    }
}
