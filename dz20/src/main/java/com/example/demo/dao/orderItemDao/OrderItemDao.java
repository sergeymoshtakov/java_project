package com.example.demo.dao.orderItemDao;

import com.example.demo.database.DatabaseConnection;
import com.example.demo.model.OrderItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderItemDao implements IOrderItemDao {

    private static final String SAVE_ORDER_ITEM = "INSERT INTO order_items (order_id, menu_item_id, quantity, price) VALUES (?, ?, ?, ?)";
    private static final String UPDATE_ORDER_ITEM = "UPDATE order_items SET order_id = ?, menu_item_id = ?, quantity = ?, price = ? WHERE id = ?";
    private static final String FIND_ALL_ORDER_ITEMS = "SELECT oi.id AS order_item_id, oi.quantity, oi.price, " +
            "mi.id AS menu_item_id, mi.name_en, mi.name_de, mi.price AS menu_item_price, " +
            "it.id AS item_type_id, it.name AS item_type_name " +
            "FROM order_items oi " +
            "LEFT JOIN menu_items mi ON oi.menu_item_id = mi.id " +
            "LEFT JOIN item_types it ON mi.item_type_id = it.id;";
    private static final String FIND_ORDER_ITEM_BY_ID = "SELECT * FROM order_items WHERE id = ?";
    private static final String DELETE_ORDER_ITEM = "DELETE FROM order_items WHERE id = ?";
    private static final String DELETE_ALL_ORDER_ITEMS = "DELETE FROM order_items";

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderItemDao.class);

    private static final OrderItemRowMapper orderItemRowMapper = new OrderItemRowMapper();

    @Override
    public void save(OrderItem orderItem) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_ORDER_ITEM)) {

            preparedStatement.setInt(1, orderItem.getOrder().getId());
            preparedStatement.setInt(2, orderItem.getMenuItem().getId());
            preparedStatement.setInt(3, orderItem.getQuantity());
            preparedStatement.setDouble(4, orderItem.getPrice());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Error saving order item: {}", e.getMessage());
        }
    }

    @Override
    public void update(OrderItem orderItem) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ORDER_ITEM)) {

            preparedStatement.setInt(1, orderItem.getOrder().getId());
            preparedStatement.setInt(2, orderItem.getMenuItem().getId());
            preparedStatement.setInt(3, orderItem.getQuantity());
            preparedStatement.setDouble(4, orderItem.getPrice());
            preparedStatement.setInt(5, orderItem.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Error updating order item: {}", e.getMessage());
        }
    }

    @Override
    public void delete(int orderItemId) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ORDER_ITEM)) {

            preparedStatement.setInt(1, orderItemId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Error deleting order item: {}", e.getMessage());
        }
    }

    @Override
    public List<OrderItem> findAll() {
        List<OrderItem> orderItems = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(FIND_ALL_ORDER_ITEMS)) {

            int rowNum = 0;
            while (resultSet.next()) {
                OrderItem orderItem = orderItemRowMapper.mapRow(resultSet, rowNum++);
                orderItems.add(orderItem);
            }
        } catch (SQLException e) {
            LOGGER.error("Error finding all order items: {}", e.getMessage());
        }
        return orderItems;
    }

    @Override
    public OrderItem findById(int orderItemId) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ORDER_ITEM_BY_ID)) {

            preparedStatement.setInt(1, orderItemId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return orderItemRowMapper.mapRow(resultSet, 0);
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Error finding order item by ID: {}", e.getMessage());
        }
        return null;
    }

    @Override
    public void deleteAll() {
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement()) {

            statement.executeUpdate(DELETE_ALL_ORDER_ITEMS);
        } catch (SQLException e) {
            LOGGER.error("Error deleting all order items: {}", e.getMessage());
        }
    }
}
