package com.example.demo.dao.orderDao;

import com.example.demo.model.Order;

import java.util.List;

public interface IOrderDao {
    void save(Order order);
    void update(Order order);
    void delete(int orderId);
    List<Order> findAll();
    Order findById(int orderId);
    void deleteAll();
}
