package com.example.spring_postgres_demo.factory;

import com.example.spring_postgres_demo.dao.status.StatusRepository;
import com.example.spring_postgres_demo.model.Driver;
import com.example.spring_postgres_demo.model.Status;
import com.example.spring_postgres_demo.util.RandomElements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class DriverFactory implements IFactory {

    @Autowired
    StatusFactory statusFactory;

    @Autowired
    StatusRepository statusRepository;

    public static final String[] FIRST_NAMES = {"John", "Michael", "Anna", "Sophia", "James", "Maria", "Robert", "Emma"};
    public static final String[] LAST_NAMES = {"Smith", "Johnson", "Williams", "Jones", "Brown", "Davis", "Miller", "Wilson"};
    public static final double MIN_SALARY = 1000.0;
    public static final double MAX_SALARY = 5000.0;
    public static final int MIN_EXPERIENCE = 1;
    public static final int MAX_EXPERIENCE = 20;

    @Override
    public Driver getRandomElement() {
        Random random = new Random();

        String firstName = RandomElements.getRandomElement(FIRST_NAMES);
        String lastName = RandomElements.getRandomElement(LAST_NAMES);
        int experience = MIN_EXPERIENCE + random.nextInt(MAX_EXPERIENCE - MIN_EXPERIENCE + 1);
        double salary = MIN_SALARY + (MAX_SALARY - MIN_SALARY) * random.nextDouble();

        Status status = statusFactory.getRandomElement();
        int id = statusRepository.findIdByName(status.getName());
        status.setId(id);

        Driver driver = new Driver();
        driver.setFirstName(firstName);
        driver.setLastName(lastName);
        driver.setExperience(experience);
        driver.setSalary(salary);
        driver.setStatus(status);

        return driver;
    }
}
