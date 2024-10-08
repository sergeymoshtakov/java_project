package com.example.spring_postgres_demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PendingRequestDTO {
    private int id;
    private int destinationId;
    private int cargoTypeId;
    private double cargoWeight;
    private int statusId;
    private Timestamp creationTime;
}
