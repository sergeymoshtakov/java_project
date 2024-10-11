package com.example.spring_postgres_demo.controller;

import com.example.spring_postgres_demo.dto.EventCreationDTO;
import com.example.spring_postgres_demo.model.Place;
import com.example.spring_postgres_demo.service.eventservice.EventService;
import com.example.spring_postgres_demo.service.eventservice.EventCreationService;
import com.example.spring_postgres_demo.service.placeservice.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MenuController {

    @Autowired
    EventService eventService;

    @Autowired
    EventCreationService eventCreationService;

    @Autowired
    PlaceService placeService;

    @GetMapping("/")
    public String mainMenu() {
        return "menu";
    }

    @GetMapping("/create-event")
    public String createEventPage(Model model) {
        // Получаем список всех мест и добавляем в модель
        List<Place> places = placeService.findAll();
        model.addAttribute("places", places);
        return "create-event"; // Указывает на шаблон create-event.html
    }

    @PostMapping("/create-event")
    public String createEvent(@ModelAttribute EventCreationDTO eventCreationDTO, @RequestParam("date") String date, Model model) {
        System.out.println("Received Event Creation DTO: " + eventCreationDTO);

        // Парсинг даты
        if (date != null && !date.isEmpty()) {
            eventCreationDTO.setEventDate(LocalDateTime.parse(date + "T00:00:00")); // Преобразуем строку в LocalDateTime
        } else {
            System.out.println("Дата события не указана!");
            // Можно вернуть ошибку или установить значение по умолчанию
        }

        // Проверяем инициализацию TicketPacks
        if (eventCreationDTO.getTicketPacks() == null) {
            eventCreationDTO.setTicketPacks(new ArrayList<>()); // Инициализируем, если пусто
        }

        // Логика создания нового события
        eventCreationService.createEvent(eventCreationDTO);
        return "redirect:/show-events"; // Перенаправление на список событий после создания
    }


    @GetMapping("/show-events")
    public String showEventsPage(Model model) {
        model.addAttribute("events", eventService.findAll());
        return "show-events";
    }

    @GetMapping("/find-tickets")
    public String findTicketsPage() {
        return "find-tickets";
    }

    @PostMapping("/find-tickets")
    public String findTickets(@RequestParam("eventId") Long eventId, Model model) {
        model.addAttribute("tickets", eventService.findAvailableTickets(eventId));
        return "find-tickets";
    }

    @GetMapping("/nearest-events")
    public String nearestEventsPage(Model model) {
        model.addAttribute("nearestEvents", eventService.findUpcomingEvents());
        return "nearest-events";
    }
}
