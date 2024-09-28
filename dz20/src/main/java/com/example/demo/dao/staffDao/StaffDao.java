package com.example.demo.dao.staffDao;

import com.example.demo.database.DatabaseConnection;
import com.example.demo.model.Staff;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class StaffDao implements IStaffDao {

    private static final String SAVE_STAFF = "INSERT INTO staff (first_name, last_name, phone_number, email, position_id) VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE_STAFF = "UPDATE staff SET first_name = ?, last_name = ?, phone_number = ?, email = ?, position_id = ? WHERE id = ?";
    private static final String FIND_ALL_STAFF = "SELECT s.id, s.first_name, s.last_name, s.phone_number, s.email, p.id AS position_id, p.name AS position_name " +
            "FROM staff s LEFT JOIN positions p ON s.position_id = p.id;";
    private static final String FIND_STAFF_BY_ID = "SELECT * FROM staff WHERE id = ?";
    private static final String DELETE_STAFF = "DELETE FROM staff WHERE id = ?";
    private static final String DELETE_ALL_STAFF = "DELETE FROM staff";

    private static final Logger LOGGER = LoggerFactory.getLogger(StaffDao.class);

    private static final StaffRowMapper staffRowMapper = new StaffRowMapper();

    @Override
    public void save(Staff staff) {
        int positionId = getPositionIdByName(staff.getPosition().getName());
        if (positionId == -1) {
            System.out.println("Position not found: " + staff.getPosition().getName());
            return;
        }
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_STAFF)) {

            preparedStatement.setString(1, staff.getFirstName());
            preparedStatement.setString(2, staff.getLastName());
            preparedStatement.setString(3, staff.getPhoneNumber());
            preparedStatement.setString(4, staff.getEmail());
            preparedStatement.setInt(5, positionId);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Error saving staff: {}", e.getMessage());
        }
    }

    private int getPositionIdByName(String positionName) {
        String sql = "SELECT id FROM positions WHERE name = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, positionName);
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
    public void update(Staff staff) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_STAFF)) {

            preparedStatement.setString(1, staff.getFirstName());
            preparedStatement.setString(2, staff.getLastName());
            preparedStatement.setString(3, staff.getPhoneNumber());
            preparedStatement.setString(4, staff.getEmail());
            preparedStatement.setInt(5, staff.getPosition().getId());
            preparedStatement.setInt(6, staff.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Error updating staff: {}", e.getMessage());
        }
    }

    @Override
    public void delete(int staffId) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_STAFF)) {

            preparedStatement.setInt(1, staffId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Error deleting staff: {}", e.getMessage());
        }
    }

    @Override
    public List<Staff> findAll() {
        List<Staff> staffList = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(FIND_ALL_STAFF)) {

            int rowNum = 0;
            while (resultSet.next()) {
                Staff staff = staffRowMapper.mapRow(resultSet, rowNum++);
                staffList.add(staff);
            }
        } catch (SQLException e) {
            LOGGER.error("Error finding all staff: {}", e.getMessage());
        }
        return staffList;
    }

    @Override
    public Staff findById(int staffId) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_STAFF_BY_ID)) {

            preparedStatement.setInt(1, staffId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return staffRowMapper.mapRow(resultSet, 0);
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Error finding staff by ID: {}", e.getMessage());
        }
        return null;
    }

    @Override
    public void deleteAll() {
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement()) {

            statement.executeUpdate(DELETE_ALL_STAFF);
        } catch (SQLException e) {
            LOGGER.error("Error deleting all staff: {}", e.getMessage());
        }
    }
}
