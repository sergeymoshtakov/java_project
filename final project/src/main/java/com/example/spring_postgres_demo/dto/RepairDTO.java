package com.example.spring_postgres_demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RepairDTO {
    private int id;
    private int carId; // Идентификатор автомобиля
    private String carModel; // Модель автомобиля (если необходимо)
    private int driverId; // Идентификатор водителя
    private String driverName; // Имя водителя
    private LocalDateTime repairTime; // Время ремонта
    private boolean fixed; // Флаг, указывающий, был ли ремонт выполнен
}
