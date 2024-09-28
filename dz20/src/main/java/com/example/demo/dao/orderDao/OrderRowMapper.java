package com.example.demo.dao.orderDao;

import com.example.demo.dao.orderItemDao.OrderItemRowMapper;
import com.example.demo.model.Order;
import com.example.demo.model.OrderItem;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderRowMapper implements RowMapper<Order> {
    @Override
    public Order mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        List<OrderItem> orderItems = new ArrayList<>();

        return new Order(
                resultSet.getInt("id"),
                resultSet.getString("customer_first_name"),
                resultSet.getString("customer_last_name"),
                resultSet.getTimestamp("order_date"),
                resultSet.getDouble("total_amount"),
                orderItems
        );
    }
}
