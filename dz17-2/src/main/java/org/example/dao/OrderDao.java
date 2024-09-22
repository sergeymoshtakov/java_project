package org.example.dao;

import org.example.database.DatabaseConnection;
import org.example.models.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDao {
    public static final String ADD_ORDER_SQL = "INSERT INTO orders (customer_first_name, customer_last_name, order_date, total_amount) VALUES (?, ?, ?, ?)";
    public static final String DELETE_ORDER_SQL = "DELETE FROM orders WHERE id = ?";
    public static final String GET_ALL_ORDERS_SQL = "SELECT * FROM orders";
    public static final String GET_ORDER_BY_ID_SQL = "SELECT * FROM orders WHERE id = ?";
    public static final String GET_ORDERS_BY_DATE_SQL = "SELECT * FROM orders WHERE DATE(order_date) = ?";
    public static final String GET_ORDERS_BY_DATE_RANGE_SQL = "SELECT * FROM orders WHERE DATE(order_date) BETWEEN ? AND ?";
    public static final String COUNT_DESSERT_ORDERS_SQL = "SELECT COUNT(*) FROM orders o JOIN order_items oi ON o.id = oi.order_id JOIN menu_items mi ON oi.menu_item_id = mi.id WHERE DATE(o.order_date) = ? AND mi.item_type = 'Dessert'";
    public static final String COUNT_DRINK_ORDERS_SQL = "SELECT COUNT(*) FROM orders o JOIN order_items oi ON o.id = oi.order_id JOIN menu_items mi ON oi.menu_item_id = mi.id WHERE DATE(o.order_date) = ? AND mi.item_type = 'Drink'";

    public static void addNewOrder(Order order) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(ADD_ORDER_SQL)) {
            ps.setString(1, order.getCustomerFirstName());
            ps.setString(2, order.getCustomerLastName());
            ps.setTimestamp(3, order.getOrderDate());
            ps.setDouble(4, order.getTotalAmount());
            ps.executeUpdate();
            System.out.println("Order added successfully for: " + order.getCustomerFirstName() + " " + order.getCustomerLastName());
        } catch (SQLException e) {
            System.out.println("Error adding order: " + e.getMessage());
        }
    }

    public static void deleteOrder(int orderId) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(DELETE_ORDER_SQL)) {
            ps.setInt(1, orderId);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Order with ID " + orderId + " deleted successfully.");
            } else {
                System.out.println("Order with ID " + orderId + " not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error deleting order: " + e.getMessage());
        }
    }

    public static List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_ORDERS_SQL);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Order order = new Order();
                order.setId(resultSet.getInt("id"));
                order.setCustomerFirstName(resultSet.getString("customer_first_name"));
                order.setCustomerLastName(resultSet.getString("customer_last_name"));
                order.setOrderDate(resultSet.getTimestamp("order_date"));
                order.setTotalAmount(resultSet.getDouble("total_amount"));

                orders.add(order);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving orders: " + e.getMessage());
        }
        return orders;
    }

    public static List<Order> getOrdersByDate(Date date) {
        List<Order> orders = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ORDERS_BY_DATE_SQL)) {
            preparedStatement.setDate(1, date);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Order order = new Order();
                order.setId(resultSet.getInt("id"));
                order.setCustomerFirstName(resultSet.getString("customer_first_name"));
                order.setCustomerLastName(resultSet.getString("customer_last_name"));
                order.setOrderDate(resultSet.getTimestamp("order_date"));
                order.setTotalAmount(resultSet.getDouble("total_amount"));
                orders.add(order);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving orders by date: " + e.getMessage());
        }
        return orders;
    }

    public static List<Order> getOrdersByDateRange(Date startDate, Date endDate) {
        List<Order> orders = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ORDERS_BY_DATE_RANGE_SQL)) {
            preparedStatement.setDate(1, startDate);
            preparedStatement.setDate(2, endDate);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Order order = new Order();
                order.setId(resultSet.getInt("id"));
                order.setCustomerFirstName(resultSet.getString("customer_first_name"));
                order.setCustomerLastName(resultSet.getString("customer_last_name"));
                order.setOrderDate(resultSet.getTimestamp("order_date"));
                order.setTotalAmount(resultSet.getDouble("total_amount"));
                orders.add(order);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving orders by date range: " + e.getMessage());
        }
        return orders;
    }

    public static int countDessertOrders(Date date) {
        int count = 0;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(COUNT_DESSERT_ORDERS_SQL)) {
            preparedStatement.setDate(1, date);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                count = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("Error counting dessert orders: " + e.getMessage());
        }
        return count;
    }

    public static int countDrinkOrders(Date date) {
        int count = 0;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(COUNT_DRINK_ORDERS_SQL)) {
            preparedStatement.setDate(1, date);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                count = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("Error counting drink orders: " + e.getMessage());
        }
        return count;
    }

    // Получение заказа по ID
    public static Order getOrderById(int id) {
        Order order = null;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(GET_ORDER_BY_ID_SQL)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                order = new Order();
                order.setId(rs.getInt("id"));
                order.setCustomerFirstName(rs.getString("customer_first_name"));
                order.setCustomerLastName(rs.getString("customer_last_name"));
                order.setOrderDate(rs.getTimestamp("order_date"));
                order.setTotalAmount(rs.getDouble("total_amount"));
            }
        } catch (SQLException e) {
            System.out.println("Error fetching order by ID: " + e.getMessage());
        }
        return order;
    }
}
