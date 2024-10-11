package com.example.spring_postgres_demo.service.eventservice;

import com.example.spring_postgres_demo.dao.event.EventRepository;
import com.example.spring_postgres_demo.dao.place.PlaceRepository;
import com.example.spring_postgres_demo.dao.ticket.TicketRepository;
import com.example.spring_postgres_demo.model.Event;
import com.example.spring_postgres_demo.model.Place;
import com.example.spring_postgres_demo.model.Ticket;
import com.example.spring_postgres_demo.dto.EventCreationDTO; // Импортируйте ваш DTO
import com.example.spring_postgres_demo.dto.TicketPackDTO; // Импортируйте ваш DTO
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventService implements IEventService {

    private final EventRepository eventRepository;
    private final PlaceRepository placeRepository;
    private final TicketRepository ticketRepository;

    @Autowired
    public EventService(EventRepository eventRepository, PlaceRepository placeRepository, TicketRepository ticketRepository) {
        this.eventRepository = eventRepository;
        this.placeRepository = placeRepository;
        this.ticketRepository = ticketRepository;
    }

    // Сохранение нового или обновленного объекта Event
    @Override
    public void save(Event event) {
        eventRepository.save(event);
    }

    // Сохранение списка событий Event
    @Override
    public long[] saveEventsList(List<Event> events) {
        eventRepository.saveAll(events);
        return events.stream().mapToLong(Event::getId).toArray();
    }

    // Обновление существующего объекта Event
    @Override
    public void update(Event event) {
        if (event.getId() != 0) {
            eventRepository.save(event);
        }
    }

    // Удаление объекта Event
    @Override
    public void delete(Event event) {
        eventRepository.delete(event);
    }

    // Поиск всех объектов Event
    @Override
    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    // Удаление всех объектов Event
    @Override
    public void deleteAll() {
        eventRepository.deleteAll();
    }

    // Поиск объекта Event по идентификатору
    @Override
    public Event findById(long id) {
        return eventRepository.findById(id).orElse(null);
    }

    // Поиск идентификатора объекта Event по его имени
    public long findIdByName(String name) {
        List<Integer> ids = eventRepository.findIdByName(name);
        if (!ids.isEmpty()) {
            return ids.get(0);
        }
        throw new EntityNotFoundException("No Event found with name: " + name);
    }


    @Override
    @Transactional
    public List<Ticket> findAvailableTickets(Long eventId) {
        // Ищем события по ID
        Event event = findById(eventId);
        if (event == null) {
            throw new EntityNotFoundException("Event not found with ID: " + eventId);
        }
        // Получаем все билеты на событие и фильтруем по статусу (например, по наличию)
        return ticketRepository.findByEvent(event); // Предполагается, что в TicketRepository есть этот метод
    }

    @Override
    public List<Event> findUpcomingEvents() {
        return findAll().stream()
                .filter(event -> event.getEventDate() != null && event.getEventDate().isAfter(LocalDateTime.now()))
                .collect(Collectors.toList());
    }

    // Создание нового ивента из EventCreationDTO
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
