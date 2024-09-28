package com.example.demo.factories;

import com.example.demo.model.Position;
import com.example.demo.util.RandomElements;
import org.springframework.stereotype.Service;

@Service
public class PositionFactory {
    public static final String[] POSITION_NAMES = {"Barista", "Confectioner", "Waiter", "Cashier", "Cleaner"};

    public static Position createRandomPosition() {
        String name = RandomElements.getRandomElement(POSITION_NAMES);
        return new Position(0, name);
    }
}
