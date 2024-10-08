package com.example.spring_postgres_demo.util;

import com.example.spring_postgres_demo.factory.*;
import com.example.spring_postgres_demo.service.car.CarService;
import com.example.spring_postgres_demo.enums.Statuses;
import com.example.spring_postgres_demo.model.Car;
import com.example.spring_postgres_demo.model.CargoType;
import com.example.spring_postgres_demo.model.Destination;
import com.example.spring_postgres_demo.model.Driver;
import com.example.spring_postgres_demo.model.Status;
import com.example.spring_postgres_demo.service.cargoType.CargoTypeService;
import com.example.spring_postgres_demo.service.destination.DestinationService;
import com.example.spring_postgres_demo.service.driver.DriverService;
import com.example.spring_postgres_demo.service.pendingRequest.PendingRequestService;
import com.example.spring_postgres_demo.service.repair.RepairService;
import com.example.spring_postgres_demo.service.request.RequestService;
import com.example.spring_postgres_demo.service.statistics.StatisticsService;
import com.example.spring_postgres_demo.service.status.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutobaseDBInitializer{

    @Autowired
    private CarFactory carFactory;

    @Autowired
    private CargoTypeFactory cargoTypeFactory;

    @Autowired
    private DestinationFactory destinationFactory;

    @Autowired
    private DriverFactory driverFactory;

    @Autowired
    private CarService carService;

    @Autowired
    private CargoTypeService cargoTypeService;

    @Autowired
    private DestinationService destinationService;

    @Autowired
    private DriverService driverService;

    @Autowired
    private StatusService statusService;

    @Autowired
    private PendingRequestService pendingRequestService;

    @Autowired
    private RequestService requestService;

    @Autowired
    private RepairService repairService;

    @Autowired
    private StatisticsService statisticsService;

    public void cleanAll(){
        repairService.deleteAll();
        requestService.deleteAll();
        pendingRequestService.deleteAll();
        carService.deleteAll();
        driverService.deleteAll();
        statisticsService.deleteAll();
        statusService.deleteAll();
        destinationService.deleteAll();
        cargoTypeService.deleteAll();
    }

    public void initializeStatuses() {
        for (Statuses status : Statuses.values()) {
            Status statusEntity = new Status();
            statusEntity.setId(status.getId());
            statusEntity.setName(status.getName());
            statusService.save(statusEntity);
        }
    }

    public void initializeCars(int count) {
        for (int i = 0; i < count; i++) {
            Car car = carFactory.getRandomElement();
            carService.save(car);
        }
    }

    public void initializeCargoTypes(int count) {
        for (int i = 0; i < count; i++) {
            CargoType cargoType = cargoTypeFactory.getRandomElement();
            cargoTypeService.save(cargoType);
        }
    }

    public void initializeDestinations(int count) {
        for (int i = 0; i < count; i++) {
            Destination destination = destinationFactory.getRandomElement();
            destinationService.save(destination);
        }
    }

    public void initializeDrivers(int count) {
        for (int i = 0; i < count; i++) {
            Driver driver = driverFactory.getRandomElement();
            driverService.save(driver);
        }
    }
}