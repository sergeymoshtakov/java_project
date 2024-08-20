package com.company.factories;

import com.company.model.Car;
import com.company.util.RandomElements;

public class CarFactory {
    public static final String[] names = {"Mustang", "Camaro", "Civic", "Corolla", "Charger"};
    public static final String[] producers = {"Ford", "Chevrolet", "Honda", "Toyota", "Dodge"};
    public static final int[] years = {1965, 1970, 1995, 2005, 2015, 2020};
    public static final double[] volumes = {2.0, 2.5, 3.0, 4.0, 5.0};

    public static Car getRandomCar() {
        return new Car(
                RandomElements.getRandomElement(names),
                RandomElements.getRandomElement(producers),
                RandomElements.getRandomElement(years),
                RandomElements.getRandomElement(volumes)
        );
    }
}
