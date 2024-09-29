package com.example.spring_postgres_demo.factory;

import com.example.spring_postgres_demo.model.Car;
import com.example.spring_postgres_demo.model.Driver;
import com.example.spring_postgres_demo.model.Repair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Random;

@Service
public class RepairFactory implements IFactory {

    @Autowired
    private CarFactory carFactory;

    @Autowired
    private DriverFactory driverFactory;

    @Override
    public Repair getRandomElement() {
        Random random = new Random();

        Car randomCar = carFactory.getRandomElement();
        Driver randomDriver = driverFactory.getRandomElement();

        boolean isFixed = random.nextBoolean();

        Timestamp repairTime = Timestamp.from(Instant.now());

        Repair repair = new Repair();
        repair.setCar(randomCar);
        repair.setDriver(randomDriver);
        repair.setRepairTime(repairTime);
        repair.setFixed(isFixed);

        return repair;
    }
}
