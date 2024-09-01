package com.company.factories;

import com.company.interfaces.IHouse;
import com.company.interfaces.IHouseFactory;
import com.company.models.Hospital;
import com.company.utils.RandomElements;

public class HospitalFactory implements IHouseFactory {
    public static final int [] places = {100, 1000, 2000, 3000, 4000, 5000};
    public static Hospital getHouse(String address) {
        return new Hospital(address, RandomElements.getRandomElement(places));
    }
}
