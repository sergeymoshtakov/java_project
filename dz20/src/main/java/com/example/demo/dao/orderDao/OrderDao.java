package com.example.demo.dao.orderDao;

import com.example.demo.database.DatabaseConnection;
import com.example.demo.model.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderDao implements IOrderDao {

    private static final String SAVE_ORDER = "INSERT INTO orders (customer_first_name, customer_last_name, order_date, total_amount) VALUES (?, ?, ?, ?)";
    private static final String UPDATE_ORDER = "UPDATE orders SET customer_first_name = ?, customer_last_name = ?, order_date = ?, total_amount = ? WHERE id = ?";
    private static final String FIND_ALL_ORDERS = "SELECT * FROM orders";
    private static final String FIND_ORDER_BY_ID = "SELECT * FROM orders WHERE id = ?";
    private static final String DELETE_ORDER = "DELETE FROM orders WHERE id = ?";
    private static final String DELETE_ALL_ORDERS = "DELETE FROM orders";

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderDao.class);

    private static final OrderRowMapper orderRowMapper = new OrderRowMapper();

    @Override
    public void save(Order order) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_ORDER)) {

            preparedStatement.setString(1, order.getCustomerFirstName());
            preparedStatement.setString(2, order.getCustomerLastName());
            preparedStatement.setTimestamp(3, order.getOrderDate());
            preparedStatement.setDouble(4, order.getTotalAmount());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Error saving order: {}", e.getMessage());
        }
    }

    @Override
    public void update(Order order) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ORDER)) {

            preparedStatement.setString(1, order.getCustomerFirstName());
            preparedStatement.setString(2, order.getCustomerLastName());
            preparedStatement.setTimestamp(3, order.getOrderDate());
            preparedStatement.setDouble(4, order.getTotalAmount());
            preparedStatement.setInt(5, order.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Error updating order: {}", e.getMessage());
        }
    }

    @Override
    public void delete(int orderId) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ORDER)) {

            preparedStatement.setInt(1, orderId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Error deleting order: {}", e.getMessage());
        }
    }

    @Override
    public List<Order> findAll() {
        List<Order> orders = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(FIND_ALL_ORDERS)) {

            int rowNum = 0;
            while (resultSet.next()) {
                Order order = orderRowMapper.mapRow(resultSet, rowNum++);
                orders.add(order);
            }
        } catch (SQLException e) {
            LOGGER.error("Error finding all orders: {}", e.getMessage());
        }
        return orders;
    }

    @Override
    public Order findById(int orderId) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ORDER_BY_ID)) {

            preparedStatement.setInt(1, orderId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return orderRowMapper.mapRow(resultSet, 0);
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Error finding order by ID: {}", e.getMessage());
        }
        return null;
    }

    @Override
    public void deleteAll() {
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement()) {

            statement.executeUpdate(DELETE_ALL_ORDERS);
        } catch (SQLException e) {
            LOGGER.error("Error deleting all orders: {}", e.getMessage());
        }
    }
}
