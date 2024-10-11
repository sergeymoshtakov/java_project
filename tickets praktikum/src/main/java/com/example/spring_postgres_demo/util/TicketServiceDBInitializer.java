package com.example.spring_postgres_demo.util;

import com.example.spring_postgres_demo.enums.Roles;
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
import com.example.spring_postgres_demo.service.roleservice.RoleService;
import com.example.spring_postgres_demo.service.statusservice.StatusService;
import com.example.spring_postgres_demo.service.ticketservice.TicketService;
import com.example.spring_postgres_demo.service.userservice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private RoleService roleService;
    @Autowired
    private UserService userService;

    @Autowired
    private EventFactory eventFactory;
    @Autowired
    private PlaceFactory placeFactory;
    @Autowired
    private CustomerFactory customerFactory;
    @Autowired
    private TicketFactory ticketFactory;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void cleanAll(){
        userService.deleteAll();
        roleService.deleteAll();
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

    public void initializeRoles() {
        for (Roles role : Roles.values()) {
            Role roleEntity = new Role();
            roleEntity.setId(role.getId());
            roleEntity.setName(role.getRole());
            roleService.save(roleEntity);
        }
    }

    public void initializeUsers(){
        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword(passwordEncoder.encode("admin123")); // Зашифрованный пароль
        admin.setRole(roleService.findByName(Roles.ADMIN.getRole()));
        admin.setEnabled(true);
        userService.save(admin);

        User user = new User();
        user.setUsername("user");
        user.setPassword(passwordEncoder.encode("password")); // Зашифрованный пароль
        user.setRole(roleService.findByName(Roles.USER.getRole()));
        user.setEnabled(true);
        userService.save(user);
    }
}
