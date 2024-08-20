package com.company.factories;

import com.company.model.Country;
import com.company.util.RandomElements;

public class CountryFactory {
    public static final String[] names = {"United States", "Ukraine", "United Kingdom"};
    public static final String[] continents = {"America", "Europe", "Asia"};
    public static final int[] populations = {800, 777, 228};
    public static final String[] telephoneCodes = {"480", "970", "777"};
    public static final String[] capitals = {"London", "Washington", "Kyiv"};
    public static final String[] cities = {"New York", "Los Angeles", "Edinburgh", "Odessa", "Lvov"};

    public static Country getRandomCountry() {
        return new Country(
                RandomElements.getRandomElement(names),
                RandomElements.getRandomElement(continents),
                RandomElements.getRandomElement(populations),
                RandomElements.getRandomElement(telephoneCodes),
                RandomElements.getRandomElement(capitals),
                cities
        );
    }
}
