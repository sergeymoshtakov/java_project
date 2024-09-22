package org.example.dao;

import org.example.database.DatabaseConnection;
import org.example.models.ItemType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemTypeDao {
    private static final String ADD_ITEM_TYPE_SQL = "INSERT INTO item_types (name) VALUES (?)";
    private static final String DELETE_ITEM_TYPE_SQL = "DELETE FROM item_types WHERE name = ?";
    private static final String GET_ALL_ITEM_TYPES_SQL = "SELECT * FROM item_types";
    private static final String GET_ITEM_TYPE_BY_ID_SQL = "SELECT * FROM item_types WHERE id = ?"; // Новый SQL-запрос


    public static void addNewItemType(ItemType itemType) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(ADD_ITEM_TYPE_SQL)) {
            ps.setString(1, itemType.getName());
            ps.executeUpdate();
            System.out.println("Item type added successfully: " + itemType.getName());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void deleteItemType(ItemType itemType) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ITEM_TYPE_SQL)) {
            preparedStatement.setString(1, itemType.getName());
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.printf("Item type '%s' deleted successfully.%n", itemType.getName());
            } else {
                System.out.printf("Item type '%s' not found.%n", itemType.getName());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static List<ItemType> getAllItemTypes() {
        List<ItemType> itemTypes = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_ITEM_TYPES_SQL);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                ItemType itemType = new ItemType();
                itemType.setId(resultSet.getInt("id"));
                itemType.setName(resultSet.getString("name"));
                itemTypes.add(itemType);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return itemTypes;
    }

    public static ItemType getItemTypeById(int id) {
        ItemType itemType = null;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ITEM_TYPE_BY_ID_SQL)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                itemType = new ItemType();
                itemType.setId(resultSet.getInt("id"));
                itemType.setName(resultSet.getString("name"));
            }
        } catch (SQLException e) {
            System.out.println("Error fetching item type by ID: " + e.getMessage());
        }
        return itemType;
    }
}
