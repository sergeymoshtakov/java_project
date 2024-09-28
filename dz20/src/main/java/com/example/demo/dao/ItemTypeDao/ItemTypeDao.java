package com.example.demo.dao.ItemTypeDao;

import com.example.demo.database.DatabaseConnection;
import com.example.demo.model.ItemType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ItemTypeDao implements IItemTypeDao {private static final String SAVE_ITEM_TYPE = "INSERT INTO item_types (name) VALUES (?)";
    private static final String UPDATE_ITEM_TYPE = "UPDATE item_types SET name = ? WHERE id = ?";
    private static final String FIND_ALL_ITEM_TYPES = "SELECT * FROM item_types";
    private static final String FIND_ITEM_TYPE_BY_ID = "SELECT * FROM item_types WHERE id = ?";
    private static final String DELETE_ITEM_TYPE = "DELETE FROM item_types WHERE id = ?";
    private static final String DELETE_ALL_ITEM_TYPES = "DELETE FROM item_types";

    private static final Logger LOGGER = LoggerFactory.getLogger(ItemTypeDao.class);

    private static final ItemTypeRowMapper itemTypeRowMapper = new ItemTypeRowMapper();

    @Override
    public void save(ItemType itemType) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_ITEM_TYPE)) {

            preparedStatement.setString(1, itemType.getName());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Error saving item type: {}", e.getMessage());
        }
    }

    @Override
    public void update(ItemType itemType) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ITEM_TYPE)) {

            preparedStatement.setString(1, itemType.getName());
            preparedStatement.setInt(2, itemType.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Error updating item type: {}", e.getMessage());
        }
    }

    @Override
    public void delete(int itemTypeId) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ITEM_TYPE)) {

            preparedStatement.setInt(1, itemTypeId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Error deleting item type: {}", e.getMessage());
        }
    }

    @Override
    public List<ItemType> findAll() {
        List<ItemType> itemTypes = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(FIND_ALL_ITEM_TYPES)) {

            int rowNum = 0;
            while (resultSet.next()) {
                ItemType itemType = itemTypeRowMapper.mapRow(resultSet, rowNum++);
                itemTypes.add(itemType);
            }
        } catch (SQLException e) {
            LOGGER.error("Error finding all item types: {}", e.getMessage());
        }
        return itemTypes;
    }

    @Override
    public ItemType findById(int itemTypeId) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ITEM_TYPE_BY_ID)) {

            preparedStatement.setInt(1, itemTypeId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return itemTypeRowMapper.mapRow(resultSet, 0);
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Error finding item type by ID: {}", e.getMessage());
        }
        return null;
    }

    @Override
    public void deleteAll() {
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement()) {

            statement.executeUpdate(DELETE_ALL_ITEM_TYPES);
        } catch (SQLException e) {
            LOGGER.error("Error deleting all item types: {}", e.getMessage());
        }
    }
}