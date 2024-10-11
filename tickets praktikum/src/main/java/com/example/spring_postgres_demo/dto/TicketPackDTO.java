package com.example.spring_postgres_demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TicketPackDTO {
    private double cost;
    private int count;
}
