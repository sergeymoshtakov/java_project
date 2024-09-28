package com.example.demo.factories;

import com.example.demo.model.Order;
import com.example.demo.model.OrderItem;
import com.example.demo.util.RandomElements;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderFactory {
    private static final String[] FIRST_NAMES = {"John", "Jane", "Alice", "Bob", "Charlie"};
    private static final String[] LAST_NAMES = {"Doe", "Smith", "Johnson", "Williams", "Brown"};

    public static Order createRandomOrder() {
        String firstName = RandomElements.getRandomElement(FIRST_NAMES);
        String lastName = RandomElements.getRandomElement(LAST_NAMES);
        Timestamp orderDate = Timestamp.valueOf(LocalDateTime.now());
        double totalAmount = Math.round((20.0 + Math.random() * 50) * 100.0) / 100.0;

        List<OrderItem> orderItems = new ArrayList<>();
        int numberOfItems = RandomElements.getRandomElement(new int[]{1, 2, 3, 4});
        for (int i = 0; i < numberOfItems; i++) {
            OrderItem orderItem = OrderItemFactory.createRandomOrderItem();
            orderItem.setPrice(orderItem.getMenuItem().getPrice());
            orderItems.add(orderItem);
        }

        return new Order(0, firstName, lastName, orderDate, totalAmount, orderItems);
    }
}
