package com.example.spring_postgres_demo.factory;

import com.example.spring_postgres_demo.model.Place;
import com.example.spring_postgres_demo.util.RandomElements;
import org.springframework.stereotype.Service;

@Service
public class PlaceFactory implements IFactory {
    public static final String[] NAMES = {"Central Park", "Grand Theater", "City Hall", "Downtown Stadium"};
    public static final String[] ADDRESSES = {
            "123 Main St, New York, NY",
            "456 Broadway Ave, Los Angeles, CA",
            "789 Elm St, Chicago, IL",
            "101 Maple St, San Francisco, CA"
    };

    @Override
    public Place getRandomElement() {

        String name = RandomElements.getRandomElement(NAMES);
        String address = RandomElements.getRandomElement(ADDRESSES);

        Place place = new Place();
        place.setName(name);
        place.setAddress(address);

        return place;
    }
}
