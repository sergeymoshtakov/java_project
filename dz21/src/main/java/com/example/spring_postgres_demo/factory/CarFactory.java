package com.example.spring_postgres_demo.factory;

import com.example.spring_postgres_demo.dao.status.StatusRepository;
import com.example.spring_postgres_demo.model.Car;
import com.example.spring_postgres_demo.model.Status;
import com.example.spring_postgres_demo.util.RandomElements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.env.RandomValuePropertySourceEnvironmentPostProcessor;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class CarFactory implements IFactory {

    @Autowired
    StatusRepository statusRepository;

    public static final String[] CAR_MODELS = {
            "Volvo FH", "Mercedes-Benz Actros", "Scania R500",
            "MAN TGX", "DAF XF", "Iveco Stralis", "Renault Magnum",
            "Kenworth T680", "Peterbilt 579", "Freightliner Cascadia"
    };

    public static final double MIN_LOAD_CAPACITY = 5000.0;
    public static final double MAX_LOAD_CAPACITY = 20000.0;

    @Override
    public Car getRandomElement() {
        Random random = new Random();

        String model = RandomElements.getRandomElement(CAR_MODELS);
        double loadCapacity = MIN_LOAD_CAPACITY + (MAX_LOAD_CAPACITY - MIN_LOAD_CAPACITY) * random.nextDouble();
        boolean isBroken = random.nextBoolean();

        Status status = RandomElements.getRandomElement(statusRepository.findAll());

        Car car = new Car();
        car.setModel(model);
        car.setLoadCapacity(loadCapacity);
        car.setBroken(isBroken);
        car.setStatus(status);

        return car;
    }
}
