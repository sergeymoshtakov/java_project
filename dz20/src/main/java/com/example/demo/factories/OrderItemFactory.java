package com.example.demo.factories;

import com.example.demo.model.MenuItem;
import com.example.demo.model.OrderItem;
import com.example.demo.util.RandomElements;
import org.springframework.stereotype.Service;

@Service
public class OrderItemFactory {
    public static OrderItem createRandomOrderItem() {
        MenuItem menuItem = MenuItemFactory.createRandomMenuItem();
        int quantity = RandomElements.getRandomElement(new int[]{1, 2, 3});

        return new OrderItem(0, null, menuItem, quantity, menuItem.getPrice());
    }
}
