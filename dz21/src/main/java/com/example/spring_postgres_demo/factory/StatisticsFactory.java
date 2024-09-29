package com.example.spring_postgres_demo.factory;

import com.example.spring_postgres_demo.model.CargoType;
import com.example.spring_postgres_demo.model.Destination;
import com.example.spring_postgres_demo.model.Statistics;
import com.example.spring_postgres_demo.util.RandomElements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Random;

@Service
public class StatisticsFactory implements IFactory {

    @Autowired
    private DestinationFactory destinationFactory;

    @Autowired
    private CargoTypeFactory cargoTypeFactory;

    private static final String[] FIRST_NAMES = {
            "John", "Alice", "Robert", "Emma", "Michael", "Olivia", "James", "Sophia"
    };

    private static final String[] LAST_NAMES = {
            "Smith", "Johnson", "Williams", "Brown", "Jones", "Garcia", "Miller", "Davis"
    };

    @Override
    public Statistics getRandomElement() {
        Random random = new Random();

        String randomFirstName = RandomElements.getRandomElement(FIRST_NAMES);
        String randomLastName = RandomElements.getRandomElement(LAST_NAMES);

        Destination randomDestination = destinationFactory.getRandomElement();
        CargoType randomCargoType = cargoTypeFactory.getRandomElement();

        double randomCargoWeight = 1000 + (4000 * random.nextDouble());

        double randomIncome = 5000 + (15000 * random.nextDouble());

        Timestamp randomTimestamp = Timestamp.from(Instant.now().minus(random.nextInt(30), java.time.temporal.ChronoUnit.DAYS)); // Минус до 30 дней от текущей даты

        Statistics statistics = new Statistics();
        statistics.setDriverFirstName(randomFirstName);
        statistics.setDriverLastName(randomLastName);
        statistics.setDestination(randomDestination);
        statistics.setCargoType(randomCargoType);
        statistics.setCargoWeight(randomCargoWeight);
        statistics.setIncome(randomIncome);
        statistics.setTimestamp(randomTimestamp);

        return statistics;
    }
}
