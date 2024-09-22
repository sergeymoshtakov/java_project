package org.example.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class OrderItem {
    private int id;
    private Order order;
    private MenuItem menuItem;
    private int quantity;
    private double price;
}
