package com.example.spring_postgres_demo.util;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class TicketServiceInitializer {

    private final TicketServiceDBInitializer ticketServiceDBInitializer;

    @Autowired
    public TicketServiceInitializer(TicketServiceDBInitializer ticketServiceDBInitializer) {
        this.ticketServiceDBInitializer = ticketServiceDBInitializer;
    }

    public void init() {
        log.info("Инициализация базы данных...");

        ticketServiceDBInitializer.cleanAll();
        ticketServiceDBInitializer.initializeStatuses();
        ticketServiceDBInitializer.initializeCustomers(10);
        ticketServiceDBInitializer.initializePlaces(5);
        ticketServiceDBInitializer.initializeEvents(5);
        ticketServiceDBInitializer.initializeTickets(5);
        ticketServiceDBInitializer.initializeRoles();
        ticketServiceDBInitializer.initializeUsers();

        log.info("База данных успешно инициализирована.");
    }
}
