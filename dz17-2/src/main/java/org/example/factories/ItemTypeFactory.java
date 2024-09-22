package org.example.factories;

import org.example.models.ItemType;
import org.example.util.RandomElements;

public class ItemTypeFactory {
    public static final String[] ITEM_TYPE_NAMES = {"Drink", "Dessert"};

    public static ItemType createRandomItemType() {
        String name = RandomElements.getRandomElement(ITEM_TYPE_NAMES);
        return new ItemType(0, name);
    }
}
