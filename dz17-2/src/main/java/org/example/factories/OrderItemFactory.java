package org.example.factories;

import org.example.models.OrderItem;
import org.example.models.MenuItem;
import org.example.util.RandomElements;

public class OrderItemFactory {
    public static OrderItem createRandomOrderItem() {
        MenuItem menuItem = MenuItemFactory.createRandomMenuItem(); // Создание случайного элемента меню
        int quantity = RandomElements.getRandomElement(new int[]{1, 2, 3}); // Случайное количество

        return new OrderItem(0, null, menuItem, quantity, menuItem.getPrice()); // Устанавливаем ID и цену
    }
}
