package com.example.spring_postgres_demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatisticsDTO {
    private int id;
    private String driverFirstName;
    private String driverLastName;
    private String destinationName;
    private String cargoTypeName;
    private double cargoWeight;
    private double income;
    private Timestamp timestamp;
}
