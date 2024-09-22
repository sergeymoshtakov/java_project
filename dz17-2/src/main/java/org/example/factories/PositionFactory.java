package org.example.factories;

import org.example.models.Position;
import org.example.util.RandomElements;

public class PositionFactory {
    public static final String[] POSITION_NAMES = {"Barista", "Confectioner", "Waiter", "Cashier", "Cleaner"};

    public static Position createRandomPosition() {
        String name = RandomElements.getRandomElement(POSITION_NAMES);
        return new Position(0, name);
    }
}
