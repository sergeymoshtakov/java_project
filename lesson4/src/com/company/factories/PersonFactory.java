package com.company.factories;

import com.company.model.Person;
import com.company.util.RandomElements;

import java.time.LocalDate;

public class PersonFactory {
    public static final String[] names = {"Sergey", "Oleg", "Daniil", "Zahar"};
    public static final String[] surnames = {"Ivanov", "Petrov", "Sidorov"};
    public static final String[] patronymics = {"Aleksandrovich", "Albertovich", "Petrovich"};
    public static final String[] phoneNumbers = {"+3805234532", "+3803454235", "+380573457"};
    public static final String[] cities = {"New York", "London", "Washington"};
    public static final String[] countries = {"United Kingdom", "United States"};
    public static final String[] addresses = {"Avenue 312", "Londonstreet 228", "New Yorkstreet 777"};

    public static Person getRandomPerson() {
        return new Person(
                RandomElements.getRandomElement(names),
                RandomElements.getRandomElement(surnames),
                RandomElements.getRandomElement(patronymics),
                LocalDate.now(),
                RandomElements.getRandomElement(phoneNumbers),
                RandomElements.getRandomElement(cities),
                RandomElements.getRandomElement(countries),
                RandomElements.getRandomElement(addresses)
        );
    }
}
