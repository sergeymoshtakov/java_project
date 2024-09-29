package com.example.spring_postgres_demo.factory;

import com.example.spring_postgres_demo.model.Destination;
import com.example.spring_postgres_demo.util.RandomElements;
import org.springframework.stereotype.Service;

@Service
public class DestinationFactory implements IFactory {
    public static final String[] DESTINATIONS
            = {"Odessa", "Kyiv", "Lviv", "Warsaw", "Berlin", "Paris", "London", "New York"};
    @Override
    public Destination getRandomElement() {
        String name = RandomElements.getRandomElement(DESTINATIONS);
        Destination destination = new Destination();
        destination.setName(name);
        return destination;
    }
}
