package com.example.spring_postgres_demo.service.eventservice;

import com.example.spring_postgres_demo.dao.event.EventRepository;
import com.example.spring_postgres_demo.dao.place.PlaceRepository;
import com.example.spring_postgres_demo.dao.ticket.TicketRepository;
import com.example.spring_postgres_demo.dto.EventCreationDTO;
import com.example.spring_postgres_demo.dto.TicketPackDTO;
import com.example.spring_postgres_demo.model.Event;
import com.example.spring_postgres_demo.model.Place;
import com.example.spring_postgres_demo.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventCreationService {

    private final EventRepository eventRepository;
    private final PlaceRepository placeRepository;
    private final TicketRepository ticketRepository;

    @Autowired
    public EventCreationService(EventRepository eventRepository, PlaceRepository placeRepository, TicketRepository ticketRepository) {
        this.eventRepository = eventRepository;
        this.placeRepository = placeRepository;
        this.ticketRepository = ticketRepository;
    }

    // Создание нового ивента
    public void createEvent(EventCreationDTO eventCreationDTO) {
        // Проверка существования места
        Place place = placeRepository.findById(eventCreationDTO.getPlace().getId())
                .orElseGet(() -> {
                    Place newPlace = new Place();
                    newPlace.setName(eventCreationDTO.getPlace().getName());
                    return placeRepository.save(newPlace); // Сохраняем новое место
                });

        Event event = new Event();
        event.setName(eventCreationDTO.getName());
        event.setEventDate(eventCreationDTO.getEventDate());
        event.setPlace(place);

        event = eventRepository.save(event); // Сохраняем ивент

        // Создание билетов
        for (TicketPackDTO ticketPack : eventCreationDTO.getTicketPacks()) {
            for (int i = 0; i < ticketPack.getCount(); i++) {
                Ticket ticket = new Ticket();
                ticket.setEvent(event);
                ticket.setCost(ticketPack.getCost());
                ticketRepository.save(ticket);
            }
        }
    }
}
