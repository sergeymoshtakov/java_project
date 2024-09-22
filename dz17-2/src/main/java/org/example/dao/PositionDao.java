package org.example.dao;

import org.example.database.DatabaseConnection;
import org.example.models.Position;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PositionDao {
    public static final String ADD_POSITION_SQL = "INSERT INTO positions (name) VALUES (?)";
    public static final String DELETE_POSITION_SQL = "DELETE FROM positions WHERE name = ?";
    public static final String GET_ALL_POSITIONS_SQL = "SELECT * FROM positions";

    public static void addNewPosition(Position position) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(ADD_POSITION_SQL)) {
            ps.setString(1, position.getName());
            ps.executeUpdate();
            System.out.println("Position added successfully: " + position.getName());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void deletePosition(Position position) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_POSITION_SQL)) {
            preparedStatement.setString(1, position.getName());
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.printf("Position '%s' deleted successfully.%n", position.getName());
            } else {
                System.out.printf("Position '%s' not found.%n", position.getName());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static List<Position> getAllPositions() {
        List<Position> positions = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_POSITIONS_SQL);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Position position = new Position();
                position.setId(resultSet.getInt("id"));
                position.setName(resultSet.getString("name"));
                positions.add(position);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return positions;
    }
}
