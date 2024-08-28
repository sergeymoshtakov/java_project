package com.company.factories;

import com.company.models.BanknoteCarrier;
import com.company.resources.Nominals;
import com.company.util.RandomElements;

import java.util.Random;

public class BanknoteCarrierFactory {
    public static final int DEFAULT_QUANTITY = 0;
    public static BanknoteCarrier getBanknoteCarrier() {
        Random random = new Random();
        return new BanknoteCarrier(
                RandomElements.getRandomElement(Nominals.nominals),
                DEFAULT_QUANTITY
        );
    }
    public static BanknoteCarrier getBanknoteCarrier(int nominal) {
        Random random = new Random();
        return new BanknoteCarrier(
                nominal,
                DEFAULT_QUANTITY
        );
    }
}
