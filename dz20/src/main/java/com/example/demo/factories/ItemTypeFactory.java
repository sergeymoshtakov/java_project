package com.example.demo.factories;

import com.example.demo.model.ItemType;
import com.example.demo.util.RandomElements;
import org.springframework.stereotype.Service;

@Service
public class ItemTypeFactory {
    public static final String[] ITEM_TYPE_NAMES = {"Drink", "Dessert"};

    public static ItemType createRandomItemType() {
        String name = RandomElements.getRandomElement(ITEM_TYPE_NAMES);
        return new ItemType(0, name);
    }
}
