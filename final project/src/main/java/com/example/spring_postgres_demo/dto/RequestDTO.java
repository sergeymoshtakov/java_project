package com.example.spring_postgres_demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestDTO {
    private int id;
    private int destinationId;
    private int cargoTypeId;
    private double cargoWeight;
    private int driverId;
    private int carId;
    private int statusId;
    private LocalDateTime startTime; // Keep this as LocalDateTime
    private LocalDateTime endTime;   // Keep this as LocalDateTime
}
