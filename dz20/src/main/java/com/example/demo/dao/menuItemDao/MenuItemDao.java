package com.example.demo.dao.menuItemDao;

import com.example.demo.dao.ItemTypeDao.ItemTypeDao;
import com.example.demo.database.DatabaseConnection;
import com.example.demo.model.MenuItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MenuItemDao implements IMenuItemDao {

    private static final String SAVE_MENU_ITEM = "INSERT INTO menu_items (name_en, name_de, price, item_type_id) VALUES (?, ?, ?, ?)";
    private static final String UPDATE_MENU_ITEM = "UPDATE menu_items SET name_en = ?, name_de = ?, price = ?, item_type_id = ? WHERE id = ?";
    private static final String FIND_ALL_MENU_ITEMS = "SELECT mi.id, mi.name_en, mi.name_de, mi.price, it.id AS item_type_id, it.name AS item_type_name " +
            "FROM menu_items mi " +
            "LEFT JOIN item_types it ON mi.item_type_id = it.id;";
    private static final String FIND_MENU_ITEM_BY_ID = "SELECT * FROM menu_items WHERE id = ?";
    private static final String DELETE_MENU_ITEM = "DELETE FROM menu_items WHERE id = ?";
    private static final String DELETE_ALL_MENU_ITEMS = "DELETE FROM menu_items";

    private static final Logger LOGGER = LoggerFactory.getLogger(MenuItemDao.class);

    private static final MenuItemRowMapper menuItemRowMapper = new MenuItemRowMapper();

    @Override
    public void save(MenuItem menuItem) {
        int itemTypeId = getItemTypeIdByName(menuItem.getItemType().getName());
        if (itemTypeId == -1) {
            System.out.println("Item type not found: " + menuItem.getItemType().getName());
            return;
        }
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_MENU_ITEM)) {

            preparedStatement.setString(1, menuItem.getNameEn());
            preparedStatement.setString(2, menuItem.getNameDe());
            preparedStatement.setDouble(3, menuItem.getPrice());
            preparedStatement.setInt(4, itemTypeId);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Error saving menu item: {}", e.getMessage());
        }
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

    @Override
    public void update(MenuItem menuItem) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_MENU_ITEM)) {

            preparedStatement.setString(1, menuItem.getNameEn());
            preparedStatement.setString(2, menuItem.getNameDe());
            preparedStatement.setDouble(3, menuItem.getPrice());
            preparedStatement.setInt(4, menuItem.getItemType().getId());
            preparedStatement.setInt(5, menuItem.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Error updating menu item: {}", e.getMessage());
        }
    }

    @Override
    public void delete(int menuItemId) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_MENU_ITEM)) {

            preparedStatement.setInt(1, menuItemId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Error deleting menu item: {}", e.getMessage());
        }
    }

    @Override
    public List<MenuItem> findAll() {
        List<MenuItem> menuItems = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(FIND_ALL_MENU_ITEMS)) {

            int rowNum = 0;
            while (resultSet.next()) {
                MenuItem menuItem = menuItemRowMapper.mapRow(resultSet, rowNum++);
                menuItems.add(menuItem);
            }
        } catch (SQLException e) {
            LOGGER.error("Error finding all menu items: {}", e.getMessage());
        }
        return menuItems;
    }

    @Override
    public MenuItem findById(int menuItemId) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_MENU_ITEM_BY_ID)) {

            preparedStatement.setInt(1, menuItemId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return menuItemRowMapper.mapRow(resultSet, 0);
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Error finding menu item by ID: {}", e.getMessage());
        }
        return null;
    }

    @Override
    public void deleteAll() {
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement()) {

            statement.executeUpdate(DELETE_ALL_MENU_ITEMS);
        } catch (SQLException e) {
            LOGGER.error("Error deleting all menu items: {}", e.getMessage());
        }
    }
}
