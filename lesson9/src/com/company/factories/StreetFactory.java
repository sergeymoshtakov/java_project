package com.company.factories;

import com.company.models.Hospital;
import com.company.models.Street;
import com.company.utils.RandomElements;

import java.util.Random;

public class StreetFactory {
    public static final String[] streetNames = {
            "Main Street",
            "Oak Avenue",
            "Maple Drive",
            "Elm Street",
            "Pine Lane",
            "Cedar Road",
            "Park Avenue",
            "Sunset Boulevard",
            "River Road",
            "Birch Street"
    };
    public static Street getStreet() {
        Random random = new Random();
        Street street = new Street(RandomElements.getRandomElement(streetNames));
        street.addHouse(HospitalFactory.getHouse(street.getName() + " " + random.nextInt(100)));
        return street;
    }
}
