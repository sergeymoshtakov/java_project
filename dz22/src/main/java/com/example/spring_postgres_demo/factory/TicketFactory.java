package com.example.spring_postgres_demo.factory;

import com.example.spring_postgres_demo.dao.customer.CustomerRepository;
import com.example.spring_postgres_demo.dao.event.EventRepository;
import com.example.spring_postgres_demo.dao.status.StatusRepository;
import com.example.spring_postgres_demo.model.Customer;
import com.example.spring_postgres_demo.model.Event;
import com.example.spring_postgres_demo.model.Status;
import com.example.spring_postgres_demo.model.Ticket;
import com.example.spring_postgres_demo.util.RandomElements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketFactory implements IFactory {

    @Autowired
    StatusFactory statusFactory;

    @Autowired
    StatusRepository statusRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    EventRepository eventRepository;

    // Примерные диапазоны для генерации случайных значений
    public static final double[] COSTS = {20.0, 30.5, 50.0, 75.25, 100.0};
    public static final int[] NUMBERS = {1, 2, 5, 10, 20, 50, 100};


    @Override
    public Ticket getRandomElement() {
        Ticket ticket = new Ticket();

        // Генерация случайных значений для полей
        double cost = RandomElements.getRandomElement(COSTS); // случайная стоимость
        int number = RandomElements.getRandomElement(NUMBERS); // случайный номер места

        Status status = statusFactory.getRandomElement();
        long id = statusRepository.findIdByName(status.getName()).get(0);
        status.setId(id);
        ticket.setStatus(status);

        Customer customer = RandomElements.getRandomElement(customerRepository.findAll());
        id = customer.getId();
        customer.setId(id);
        ticket.setCustomer(customer);

        Event event = RandomElements.getRandomElement(eventRepository.findAll());
        id = event.getId();
        event.setId(id);
        ticket.setEvent(event);

        // Заполнение объекта Ticket случайными данными
        ticket.setCost(cost);
        ticket.setNumber(number);
        ticket.setCustomer(customer);
        ticket.setEvent(event);

        return ticket;
    }
}
