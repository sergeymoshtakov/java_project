package com.example.demo.dao.positionDao;

import com.example.demo.database.DatabaseConnection;
import com.example.demo.model.Position;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PositionDao implements IPositionDao {

    private static final String SAVE_POSITION = "INSERT INTO positions (name) VALUES (?)";
    private static final String UPDATE_POSITION = "UPDATE positions SET name = ? WHERE id = ?";
    private static final String FIND_ALL_POSITIONS = "SELECT * FROM positions";
    private static final String FIND_POSITION_BY_ID = "SELECT * FROM positions WHERE id = ?";
    private static final String DELETE_POSITION = "DELETE FROM positions WHERE id = ?";
    private static final String DELETE_ALL_POSITIONS = "DELETE FROM positions";

    private static final Logger LOGGER = LoggerFactory.getLogger(PositionDao.class);

    private static final PositionRowMapper positionRowMapper = new PositionRowMapper();

    @Override
    public void save(Position position) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_POSITION)) {

            preparedStatement.setString(1, position.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Error saving position: {}", e.getMessage());
        }
    }

    @Override
    public void update(Position position) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_POSITION)) {

            preparedStatement.setString(1, position.getName());
            preparedStatement.setInt(2, position.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Error updating position: {}", e.getMessage());
        }
    }

    @Override
    public void delete(int positionId) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_POSITION)) {

            preparedStatement.setInt(1, positionId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Error deleting position: {}", e.getMessage());
        }
    }

    @Override
    public List<Position> findAll() {
        List<Position> positions = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(FIND_ALL_POSITIONS)) {

            int rowNum = 0;
            while (resultSet.next()) {
                Position position = positionRowMapper.mapRow(resultSet, rowNum++);
                positions.add(position);
            }
        } catch (SQLException e) {
            LOGGER.error("Error finding all positions: {}", e.getMessage());
        }
        return positions;
    }

    @Override
    public Position findById(int positionId) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_POSITION_BY_ID)) {

            preparedStatement.setInt(1, positionId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return positionRowMapper.mapRow(resultSet, 0);
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Error finding position by ID: {}", e.getMessage());
        }
        return null;
    }

    @Override
    public void deleteAll() {
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement()) {

            statement.executeUpdate(DELETE_ALL_POSITIONS);
        } catch (SQLException e) {
            LOGGER.error("Error deleting all positions: {}", e.getMessage());
        }
    }
}
