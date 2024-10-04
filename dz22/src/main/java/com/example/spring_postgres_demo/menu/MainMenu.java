package com.example.spring_postgres_demo.menu;

import com.example.spring_postgres_demo.dto.EventCreationDTO;
import com.example.spring_postgres_demo.dto.PlaceDTO;
import com.example.spring_postgres_demo.dto.TicketPackDTO;
import com.example.spring_postgres_demo.model.Event;
import com.example.spring_postgres_demo.model.Ticket;
import com.example.spring_postgres_demo.service.eventservice.EventCreationService;
import com.example.spring_postgres_demo.service.eventservice.EventService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class MainMenu {

    private final EventCreationService eventCreationService;
    private final EventService eventService;

    public MainMenu(EventCreationService eventCreationService, EventService eventService) {
        this.eventCreationService = eventCreationService;
        this.eventService = eventService;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\nМеню:");
            System.out.println("1. Создать новое событие");
            System.out.println("2. Показать все события");
            System.out.println("3. Поиск свободных билетов по событию");
            System.out.println("4. Поиск ближайших событий");
            System.out.println("5. Выход");

            System.out.print("Выберите опцию: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Очистка буфера

            switch (choice) {
                case 1:
                    createEvent(scanner);
                    break;
                case 2:
                    showAllEvents();
                    break;
                case 3:
                    findAvailableTickets(scanner);
                    break;
                case 4:
                    findUpcomingEvents();
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("Некорректный выбор. Пожалуйста, попробуйте еще раз.");
            }
        }
        scanner.close();
    }

    private void createEvent(Scanner scanner) {
        System.out.println("Введите название события: ");
        String name = scanner.nextLine();

        System.out.println("Введите дату начала события (yyyy-MM-dd HH:mm): ");
        LocalDateTime eventDate = LocalDateTime.parse(scanner.nextLine());

        System.out.println("Введите количество пакетов билетов: ");
        int ticketPackCount = scanner.nextInt();
        scanner.nextLine(); // Очистка буфера

        List<TicketPackDTO> ticketPacks = new ArrayList<>();
        for (int i = 0; i < ticketPackCount; i++) {
            System.out.println("Введите цену билета: ");
            double cost = scanner.nextDouble();

            System.out.println("Введите количество билетов: ");
            int count = scanner.nextInt();
            scanner.nextLine(); // Очистка буфера

            TicketPackDTO ticketPack = new TicketPackDTO(cost, count);
            ticketPacks.add(ticketPack);
        }

        System.out.println("Введите название места: ");
        String placeName = scanner.nextLine();
        PlaceDTO place = new PlaceDTO(null, placeName);

        EventCreationDTO eventCreationDTO = new EventCreationDTO(null, eventDate, name, ticketPacks, place);
        eventCreationService.createEvent(eventCreationDTO);
        System.out.println("Событие успешно создано.");
    }

    private void showAllEvents() {
        List<Event> events = eventService.findAll(); // Метод, который возвращает все события в нужном формате
        if (events.isEmpty()) {
            System.out.println("Нет доступных событий.");
        } else {
            for (Event event : events) {
                System.out.println(event);
            }
        }
    }

    private void findAvailableTickets(Scanner scanner) {
        System.out.println("Введите ID события для поиска свободных билетов: ");
        Long eventId = scanner.nextLong();
        List<Ticket> availableTickets = eventService.findAvailableTickets(eventId);
        if (availableTickets.isEmpty()) {
            System.out.println("Нет доступных билетов для этого события.");
        } else {
            System.out.println("Доступные билеты: ");
            availableTickets.forEach(ticket -> System.out.println(ticket));
        }
    }

    private void findUpcomingEvents() {
        List<Event> upcomingEvents = eventService.findUpcomingEvents();
        if (upcomingEvents.isEmpty()) {
            System.out.println("Нет ближайших событий.");
        } else {
            System.out.println("Ближайшие события: ");
            upcomingEvents.forEach(event -> System.out.println(event));
        }
    }
}
