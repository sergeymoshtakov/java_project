package org.example.dao;

import org.example.database.DatabaseConnection;
import org.example.models.OrderItem;
import org.example.models.MenuItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderItemDao {
    private static final String ADD_ORDER_ITEM_SQL = "INSERT INTO order_items (order_id, menu_item_id, quantity, price) VALUES (?, ?, ?, ?)";
    private static final String DELETE_ORDER_ITEM_SQL = "DELETE FROM order_items WHERE id = ?";
    private static final String GET_ALL_ORDER_ITEMS_SQL = "SELECT * FROM order_items";
    private static final String GET_MENU_ITEM_ID_SQL = "SELECT id FROM menu_items WHERE name_en = ?"; // Изменено на name_en
    private static final String GET_MENU_ITEM_BY_ID_SQL = "SELECT * FROM menu_items WHERE id = ?";

    public static void addNewOrderItem(int orderId, String menuItemName, int quantity, double price) {
        int menuItemId = getMenuItemIdByName(menuItemName);

        if (menuItemId == -1) {
            System.out.println("MenuItem not found: " + menuItemName);
            return;
        }

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(ADD_ORDER_ITEM_SQL)) {
            ps.setInt(1, orderId);
            ps.setInt(2, menuItemId);
            ps.setInt(3, quantity);
            ps.setDouble(4, price);
            ps.executeUpdate();
            System.out.println("Order item added successfully.");
        } catch (SQLException e) {
            System.out.println("Error adding order item: " + e.getMessage());
        }
    }

    public static void deleteOrderItem(int id) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(DELETE_ORDER_ITEM_SQL)) {
            ps.setInt(1, id);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Order item deleted successfully.");
            } else {
                System.out.println("Order item not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error deleting order item: " + e.getMessage());
        }
    }

    public static List<OrderItem> getAllOrderItems() {
        List<OrderItem> orderItems = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(GET_ALL_ORDER_ITEMS_SQL);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                OrderItem orderItem = new OrderItem();
                orderItem.setId(rs.getInt("id"));
                orderItem.setQuantity(rs.getInt("quantity"));
                orderItem.setPrice(rs.getDouble("price"));

                int orderId = rs.getInt("order_id");
                int menuItemId = rs.getInt("menu_item_id");

                orderItem.setOrder(OrderDao.getOrderById(orderId));
                orderItem.setMenuItem(getMenuItemById(menuItemId));

                orderItems.add(orderItem);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching order items: " + e.getMessage());
        }
        return orderItems;
    }

    private static int getMenuItemIdByName(String menuItemName) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(GET_MENU_ITEM_ID_SQL)) {
            ps.setString(1, menuItemName);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException e) {
            System.out.println("Error fetching menu item ID: " + e.getMessage());
        }
        return -1;
    }

    private static MenuItem getMenuItemById(int id) {
        MenuItem menuItem = null;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(GET_MENU_ITEM_BY_ID_SQL)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                menuItem = new MenuItem();
                menuItem.setId(rs.getInt("id"));
                menuItem.setNameEn(rs.getString("name_en"));
                menuItem.setNameDe(rs.getString("name_de"));
                menuItem.setPrice(rs.getDouble("price"));
                menuItem.setItemType(ItemTypeDao.getItemTypeById(rs.getInt("item_type_id")));
            }
        } catch (SQLException e) {
            System.out.println("Error fetching menu item by ID: " + e.getMessage());
        }
        return menuItem;
    }
}
