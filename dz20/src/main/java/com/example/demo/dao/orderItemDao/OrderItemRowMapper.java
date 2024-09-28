package com.example.demo.dao.orderItemDao;

import com.example.demo.model.ItemType;
import com.example.demo.model.MenuItem;
import com.example.demo.model.OrderItem;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderItemRowMapper implements RowMapper<OrderItem> {

    @Override
    public OrderItem mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        ItemType itemType = new ItemType(
                resultSet.getInt("item_type_id"),
                resultSet.getString("item_type_name")
        );

        MenuItem menuItem = new MenuItem(
                resultSet.getInt("menu_item_id"),
                resultSet.getString("name_en"),
                resultSet.getString("name_de"),
                resultSet.getDouble("menu_item_price"),
                itemType
        );

        return new OrderItem(
                resultSet.getInt("order_item_id"),
                null,
                menuItem,
                resultSet.getInt("quantity"),
                resultSet.getDouble("price")
        );
    }
}
