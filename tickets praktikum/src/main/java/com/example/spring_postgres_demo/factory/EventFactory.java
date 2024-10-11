package com.example.spring_postgres_demo.factory;

import com.example.spring_postgres_demo.dao.place.PlaceRepository;
import com.example.spring_postgres_demo.model.Place;
import com.example.spring_postgres_demo.util.RandomElements;
import com.example.spring_postgres_demo.model.Event;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import org.springframework.stereotype.Service;

@Service
public class EventFactory implements IFactory  {

    @Autowired
    PlaceFactory placeFactory;

    @Autowired
    PlaceRepository placeRepository;

    public static final String[] NAMES = {"Concert", "Theater Play", "Sports Match", "Art Exhibition"};

    @Override
    public Event getRandomElement() {
        String name = RandomElements.getRandomElement(NAMES);
        LocalDateTime eventDate = LocalDateTime.now().plusDays((int) (Math.random() * 30));

        Event event = new Event();
        event.setName(name);
        event.setEventDate(eventDate);

        Place place = RandomElements.getRandomElement(placeRepository.findAll());
        long id = placeRepository.findIdByName(place.getName()).get(0);
        place.setId(id);
        event.setPlace(place);

        return event;
    }
}
