package com.example.spring_postgres_demo.service.ticketservice;

import com.example.spring_postgres_demo.dao.ticket.TicketRepository;
import com.example.spring_postgres_demo.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService implements ITicketService {

    private final TicketRepository ticketRepository;

    @Autowired
    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    // Сохранение нового или обновленного объекта Ticket
    @Override
    public void save(Ticket ticket) {
        ticketRepository.save(ticket);
    }

    // Сохранение списка билетов Ticket
    @Override
    public long[] saveTicketsList(List<Ticket> tickets) {
        ticketRepository.saveAll(tickets);
        return tickets.stream().mapToLong(Ticket::getId).toArray();
    }

    // Обновление существующего объекта Ticket
    @Override
    public void update(Ticket ticket) {
        if (ticket.getId() != 0) {
            ticketRepository.save(ticket);
        }
    }

    // Удаление объекта Ticket
    @Override
    public void delete(Ticket ticket) {
        ticketRepository.delete(ticket);
    }

    // Поиск всех объектов Ticket
    @Override
    public List<Ticket> findAll() {
        return ticketRepository.findAll();
    }

    // Удаление всех объектов Ticket
    @Override
    public void deleteAll() {
        ticketRepository.deleteAll();
    }

    // Поиск объекта Ticket по идентификатору
    @Override
    public Ticket findById(long id) {
        return ticketRepository.findById(id).orElse(null);
    }
}
