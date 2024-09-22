package org.example.factories;

import org.example.models.MenuItem;
import org.example.models.ItemType;
import org.example.util.RandomElements;

public class MenuItemFactory {
    private static final String[] NAMES_EN = {"Coffee", "Tea", "Sandwich", "Cake"};
    private static final String[] NAMES_DE = {"Kaffee", "Tee", "Sandwich", "Kuchen"};
    private static final double[] PRICES = {2.5, 1.5, 5.0, 3.0};

    public static MenuItem createRandomMenuItem() {
        String nameEn = RandomElements.getRandomElement(NAMES_EN);
        String nameDe = RandomElements.getRandomElement(NAMES_DE);
        double price = RandomElements.getRandomElement(PRICES);
        ItemType itemType = ItemTypeFactory.createRandomItemType();

        return new MenuItem(0, nameEn, nameDe, price, itemType);
    }
}
