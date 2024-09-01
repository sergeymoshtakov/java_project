package com.company.factories;

import com.company.enums.AccreditationLevel;
import com.company.interfaces.IHouse;
import com.company.interfaces.IHouseFactory;
import com.company.models.School;

import java.util.Random;

public class SchoolFactory implements IHouseFactory {
    public static School getHouse(String address) {
        Random random = new Random();
        AccreditationLevel accreditationLevel = AccreditationLevel.values()[random.nextInt(AccreditationLevel.values().length)];
        return new School(address, accreditationLevel);
    }
}
