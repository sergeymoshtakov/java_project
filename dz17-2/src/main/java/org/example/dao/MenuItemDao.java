package org.example.dao;

import org.example.database.DatabaseConnection;
import org.example.models.ItemType;
import org.example.models.MenuItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MenuItemDao {
    private static final String ADD_MENU_ITEM_SQL = "INSERT INTO menu_items (name_en, name_de, price, item_type_id) VALUES (?, ?, ?, ?)";
    private static final String UPDATE_MENU_ITEM_PRICE_SQL = "UPDATE menu_items SET price = ? WHERE name_en = ?";
    private static final String DELETE_MENU_ITEM_SQL = "DELETE FROM menu_items WHERE name_en = ?";
    private static final String GET_ALL_MENU_ITEMS_SQL = "SELECT mi.*, it.name AS item_type_name FROM menu_items mi JOIN item_types it ON mi.item_type_id = it.id";

    public static void addNewMenuItem(MenuItem menuItem) {
        int itemTypeId = getItemTypeIdByName(menuItem.getItemType().getName());
        if (itemTypeId == -1) {
            System.out.println("Item type not found: " + menuItem.getItemType().getName());
            return;
        }
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(ADD_MENU_ITEM_SQL)) {
            ps.setString(1, menuItem.getNameEn());
            ps.setString(2, menuItem.getNameDe());
            ps.setDouble(3, menuItem.getPrice());
            ps.setInt(4, itemTypeId);
            ps.executeUpdate();
            System.out.println("Menu item added successfully: " + menuItem.getNameEn());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void updateMenuItemPrice(String nameEn, double newPrice) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(UPDATE_MENU_ITEM_PRICE_SQL)) {
            ps.setDouble(1, newPrice);
            ps.setString(2, nameEn);
            ps.executeUpdate();
            System.out.printf("Menu item '%s' price updated successfully to %.2f.%n", nameEn, newPrice);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void deleteMenuItem(MenuItem menuItem) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_MENU_ITEM_SQL)) {
            preparedStatement.setString(1, menuItem.getNameEn());
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.printf("Menu item '%s' deleted successfully.%n", menuItem.getNameEn());
            } else {
                System.out.printf("Menu item '%s' not found.%n", menuItem.getNameEn());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static List<MenuItem> getAllMenuItems() {
        List<MenuItem> menuItems = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_MENU_ITEMS_SQL);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                MenuItem menuItem = new MenuItem();
                menuItem.setId(resultSet.getInt("id"));
                menuItem.setNameEn(resultSet.getString("name_en"));
                menuItem.setNameDe(resultSet.getString("name_de"));
                menuItem.setPrice(resultSet.getDouble("price"));

                ItemType itemType = new ItemType();
                itemType.setId(resultSet.getInt("item_type_id"));
                itemType.setName(resultSet.getString("item_type_name"));
                menuItem.setItemType(itemType);

                menuItems.add(menuItem);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return menuItems;
    }

    public static int getItemTypeIdByName(String itemTypeName) {
        String sql = "SELECT id FROM item_types WHERE name = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, itemTypeName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("id");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return -1;
    }
}
