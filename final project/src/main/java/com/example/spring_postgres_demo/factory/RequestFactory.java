package com.example.spring_postgres_demo.factory;

import com.example.spring_postgres_demo.dao.car.CarRepository;
import com.example.spring_postgres_demo.dao.cargoType.CargoTypeRepository;
import com.example.spring_postgres_demo.dao.destination.DestinationRepository;
import com.example.spring_postgres_demo.dao.driver.DriverRepository;
import com.example.spring_postgres_demo.dao.status.StatusRepository;
import com.example.spring_postgres_demo.model.*;
import com.example.spring_postgres_demo.util.RandomElements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Random;

@Service
public class RequestFactory implements IFactory {

    @Autowired
    private DestinationRepository destinationRepository;

    @Autowired
    private CargoTypeRepository cargoTypeRepository;

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private StatusRepository statusRepository;

    @Override
    public Request getRandomElement() {
        Random random = new Random();

        Destination randomDestination = RandomElements.getRandomElement(destinationRepository.findAll());
        CargoType randomCargoType = RandomElements.getRandomElement(cargoTypeRepository.findAll());
        Driver randomDriver = RandomElements.getRandomElement(driverRepository.findAll());
        Car randomCar = RandomElements.getRandomElement(carRepository.findAll());
        Status randomStatus = RandomElements.getRandomElement(statusRepository.findAll());

        double randomCargoWeight = 500 + (1500 * random.nextDouble());

        Timestamp startTime = Timestamp.from(Instant.now());
        Timestamp endTime = Timestamp.from(Instant.now().plus(random.nextInt(10) + 1, ChronoUnit.DAYS));

        Request request = new Request();
        request.setDestination(randomDestination);
        request.setCargoType(randomCargoType);
        request.setCargoWeight(randomCargoWeight);
        request.setDriver(randomDriver);
        request.setCar(randomCar);
        request.setStatus(randomStatus);
        request.setStartTime(startTime);
        request.setEndTime(endTime);

        return request;
    }
}
