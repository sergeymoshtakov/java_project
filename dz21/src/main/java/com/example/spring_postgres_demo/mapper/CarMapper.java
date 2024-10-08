package com.example.spring_postgres_demo.mapper;

import com.example.spring_postgres_demo.dto.CarDTO;
import com.example.spring_postgres_demo.model.Car;
import com.example.spring_postgres_demo.model.Status;

public class CarMapper {

    public static CarDTO toDTO(Car car) {
        CarDTO carDTO = new CarDTO();
        carDTO.setId(car.getId());
        carDTO.setModel(car.getModel());
        carDTO.setLoadCapacity((int) car.getLoadCapacity()); // Приведение типа, если нужно
        carDTO.setAvailable(car.getStatus().getName().equalsIgnoreCase("Available"));
        return carDTO;
    }

    public static Car toEntity(CarDTO carDTO, Status status) {
        Car car = new Car();
        car.setId(carDTO.getId());
        car.setModel(carDTO.getModel());
        car.setLoadCapacity(carDTO.getLoadCapacity());
        car.setStatus(status);
        // Статус доступности можно установить на основе переданного статуса
        car.setBroken(!carDTO.isAvailable()); // Пример установки isBroken (если нужно)
        return car;
    }
}
