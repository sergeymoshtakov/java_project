package com.example.spring_postgres_demo.mapper;

import com.example.spring_postgres_demo.dto.RequestDTO;
import com.example.spring_postgres_demo.model.Request;
import com.example.spring_postgres_demo.service.cargoType.CargoTypeService;
import com.example.spring_postgres_demo.service.destination.DestinationService;
import com.example.spring_postgres_demo.service.driver.DriverService;
import com.example.spring_postgres_demo.service.car.CarService;
import com.example.spring_postgres_demo.service.status.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Component
public class RequestMapper {

    private final DestinationService destinationService;
    private final CargoTypeService cargoTypeService;
    private final DriverService driverService;
    private final CarService carService;
    private final StatusService statusService;

    @Autowired
    public RequestMapper(DestinationService destinationService,
                         CargoTypeService cargoTypeService,
                         DriverService driverService,
                         CarService carService,
                         StatusService statusService) {
        this.destinationService = destinationService;
        this.cargoTypeService = cargoTypeService;
        this.driverService = driverService;
        this.carService = carService;
        this.statusService = statusService;
    }

    public RequestDTO toDto(Request request) {
        RequestDTO dto = new RequestDTO();
        dto.setId(request.getId());
        dto.setDestinationId(request.getDestination().getId());
        dto.setCargoTypeId(request.getCargoType().getId());
        dto.setCargoWeight(request.getCargoWeight());
        dto.setDriverId(request.getDriver().getId());
        dto.setCarId(request.getCar().getId());
        dto.setStatusId(request.getStatus().getId());

        // Преобразование Timestamp в LocalDateTime
        dto.setStartTime(convertTimestampToLocalDateTime(request.getStartTime()));
        dto.setEndTime(convertTimestampToLocalDateTime(request.getEndTime()));

        return dto;
    }

    public Request toEntity(RequestDTO dto) {
        Request request = new Request();
        request.setId(dto.getId());
        request.setCargoWeight(dto.getCargoWeight());
        request.setDestination(destinationService.findById(dto.getDestinationId()));
        request.setCargoType(cargoTypeService.findById(dto.getCargoTypeId()));
        request.setDriver(driverService.findById(dto.getDriverId()));
        request.setCar(carService.findById(dto.getCarId()));
        request.setStatus(statusService.findById(dto.getStatusId()));

        // Преобразование LocalDateTime в Timestamp
        request.setStartTime(convertLocalDateTimeToTimestamp(dto.getStartTime()));
        request.setEndTime(convertLocalDateTimeToTimestamp(dto.getEndTime()));

        return request;
    }

    // Метод для преобразования Timestamp в LocalDateTime
    private LocalDateTime convertTimestampToLocalDateTime(Timestamp timestamp) {
        return timestamp != null ? timestamp.toLocalDateTime() : null;
    }

    // Метод для преобразования LocalDateTime в Timestamp
    private Timestamp convertLocalDateTimeToTimestamp(LocalDateTime localDateTime) {
        return localDateTime != null ? Timestamp.valueOf(localDateTime) : null;
    }
}
