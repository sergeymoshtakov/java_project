package com.example.spring_postgres_demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class EventDTO {
    private Long id;
    private LocalDate eventDate;
    private String name;
    private String placeName;
}
