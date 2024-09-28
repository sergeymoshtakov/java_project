package com.example.demo.dao.orderItemDao;

import com.example.demo.model.OrderItem;

import java.util.List;

public interface IOrderItemDao {
    void save(OrderItem orderItem);
    void update(OrderItem orderItem);
    void delete(int orderItemId);
    List<OrderItem> findAll();
    OrderItem findById(int orderItemId);
    void deleteAll();
}
