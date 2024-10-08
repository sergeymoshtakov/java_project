package com.example.spring_postgres_demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarDTO {
    private int id;
    private String model;
    private int loadCapacity;
    private boolean isAvailable;
}
