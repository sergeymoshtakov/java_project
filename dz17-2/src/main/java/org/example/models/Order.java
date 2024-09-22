package org.example.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Order {
    private int id;
    private String customerFirstName;
    private String customerLastName;
    private Timestamp orderDate;
    private double totalAmount;
    private List<OrderItem> orderItems;
}
