package com.company.factories;

import com.company.model.City;
import com.company.util.RandomElements;

public class CityFactory {
    public static final String[] names = {"New York", "London", "Washington"};
    public static final String[] regions = {"New York", "California", "Alaska"};
    public static final String[] countries = {"United Kingdom", "United States"};
    public static final int[] populations = {800, 777, 228};
    public static final int[] postIndexes = {4000, 3000, 2000};
    public static final String[] telephoneCodes = {"480", "970", "777"};

    public static City getRandomCity() {
        return new City(
                RandomElements.getRandomElement(names),
                RandomElements.getRandomElement(regions),
                RandomElements.getRandomElement(countries),
                RandomElements.getRandomElement(populations),
                RandomElements.getRandomElement(postIndexes),
                RandomElements.getRandomElement(telephoneCodes)
        );
    }
}
