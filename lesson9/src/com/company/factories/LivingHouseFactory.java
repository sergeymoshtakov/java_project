package com.company.factories;

import com.company.interfaces.IHouse;
import com.company.interfaces.IHouseFactory;
import com.company.models.LivingHouse;
import com.company.utils.RandomElements;

public class LivingHouseFactory implements IHouseFactory {
    public static final int[] populations = {100, 1000, 2000, 3000, 4000, 5000};

    public static LivingHouse getHouse(String address) {
        return new LivingHouse(address, RandomElements.getRandomElement(populations));
    }
}
