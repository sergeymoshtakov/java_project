package com.company.factories;

import com.company.enums.Category;
import com.company.models.Product;
import com.company.util.RandomElements;

public class ProductFactory {
    public static final String[] names = {
            "Apple",
            "Banana",
            "Carrot",
            "Bread",
            "Milk",
            "Cheese",
            "Chicken",
            "Beef",
            "Fish",
            "Rice",
            "Pasta",
            "Tomato",
            "Potato",
            "Orange",
            "Yogurt",
            "Eggs",
            "Butter",
            "Lettuce",
            "Onion",
            "Garlic"
    };

    public static Product createProduct() {
        String randomName = RandomElements.getRandomElement(names);
        Category randomCategory = RandomElements.getRandomElement(Category.values());
        return new Product(randomName, randomCategory);
    }
}
