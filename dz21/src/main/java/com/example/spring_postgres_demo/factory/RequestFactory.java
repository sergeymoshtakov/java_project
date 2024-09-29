package com.example.spring_postgres_demo.factory;

import com.example.spring_postgres_demo.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Random;

@Service
public class RequestFactory implements IFactory {

    @Autowired
    private DestinationFactory destinationFactory;

    @Autowired
    private CargoTypeFactory cargoTypeFactory;

    @Autowired
    private DriverFactory driverFactory;

    @Autowired
    private CarFactory carFactory;

    @Autowired
    private StatusFactory statusFactory;

    @Override
    public Request getRandomElement() {
        Random random = new Random();

        Destination randomDestination = destinationFactory.getRandomElement();
        CargoType randomCargoType = cargoTypeFactory.getRandomElement();
        Driver randomDriver = driverFactory.getRandomElement();
        Car randomCar = carFactory.getRandomElement();
        Status randomStatus = statusFactory.getRandomElement();

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
