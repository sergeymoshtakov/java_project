package com.example.spring_postgres_demo.factory;

import com.example.spring_postgres_demo.dao.car.CarRepository;
import com.example.spring_postgres_demo.dao.driver.DriverRepository;
import com.example.spring_postgres_demo.model.Car;
import com.example.spring_postgres_demo.model.Driver;
import com.example.spring_postgres_demo.model.Repair;
import com.example.spring_postgres_demo.util.RandomElements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Random;

@Service
public class RepairFactory implements IFactory {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private DriverRepository driverRepository;

    @Override
    public Repair getRandomElement() {
        Random random = new Random();

        Car randomCar = RandomElements.getRandomElement(carRepository.findAll());
        Driver randomDriver = RandomElements.getRandomElement(driverRepository.findAll());

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
