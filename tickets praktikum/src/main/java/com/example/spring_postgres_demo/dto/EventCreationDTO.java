package com.example.spring_postgres_demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class EventCreationDTO {
    private Long id;
    private LocalDateTime eventDate;
    private String name;
    private List<TicketPackDTO> ticketPacks = new ArrayList<>();
    private PlaceDTO place;
}
