package org.example.dao;

import org.example.database.DatabaseConnection;
import org.example.models.Position;
import org.example.models.Staff;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StaffDao {
    public static final String ADD_STAFF_SQL = "INSERT INTO staff (first_name, last_name, phone_number, email, position_id) VALUES (?, ?, ?, ?, ?)";
    public static String UPDATE_CONFECTIONER_ADDRESS = "UPDATE staff SET email = ? WHERE position_id = (SELECT id FROM positions WHERE name = 'Confectioner') AND last_name = ?";
    public static final String UPDATE_BARISTA_PHONE_SQL = "UPDATE staff SET phone_number = ? WHERE position_id = (SELECT id FROM positions WHERE name = 'Barista') AND last_name = ?";
    public static final String DELETE_STAFF_SQL = "DELETE FROM staff WHERE first_name = ? AND last_name = ?";
    public static final String GET_ALL_STAFF_SQL = "SELECT s.*, p.name AS position_name FROM staff s JOIN positions p ON s.position_id = p.id";

    public static void addNewStaff(Staff staff) {
        int positionId = getPositionIdByName(staff.getPosition().getName());
        if (positionId == -1) {
            System.out.println("Position not found: " + staff.getPosition().getName());
            return;
        }
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(ADD_STAFF_SQL)) {
            ps.setString(1, staff.getFirstName());
            ps.setString(2, staff.getLastName());
            ps.setString(3, staff.getPhoneNumber());
            ps.setString(4, staff.getEmail());
            ps.setInt(5, positionId);
            ps.executeUpdate();
            System.out.println("Staff added successfully: " + staff.getFirstName() + " " + staff.getLastName());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void updateBaristaPhone(String lastName, String newPhone) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BARISTA_PHONE_SQL)) {
            preparedStatement.setString(1, newPhone);
            preparedStatement.setString(2, lastName);
            preparedStatement.executeUpdate();
            System.out.printf("Barista %s phone updated successfully to %s.%n", lastName, newPhone);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void updateConfectionerAddress(String confectionerName, String newEmail){
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CONFECTIONER_ADDRESS)) {
            preparedStatement.setString(1, newEmail);
            preparedStatement.setString(2, confectionerName);
            preparedStatement.executeUpdate();
            System.out.printf("Confectioner %s address updated successfully to %s.%n", confectionerName, confectionerName);
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public static void deleteStaff(Staff staff) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_STAFF_SQL)) {
            preparedStatement.setString(1, staff.getFirstName());
            preparedStatement.setString(2, staff.getLastName());
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.printf("Staff '%s %s' deleted successfully.%n", staff.getFirstName(), staff.getLastName());
            } else {
                System.out.printf("Staff '%s %s' not found.%n", staff.getFirstName(), staff.getLastName());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static List<Staff> getAllStaff() {
        List<Staff> staffList = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_STAFF_SQL);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Staff staff = new Staff();
                staff.setId(resultSet.getInt("id"));
                staff.setFirstName(resultSet.getString("first_name"));
                staff.setLastName(resultSet.getString("last_name"));
                staff.setPhoneNumber(resultSet.getString("phone_number"));
                staff.setEmail(resultSet.getString("email"));

                Position position = new Position();
                position.setId(resultSet.getInt("position_id"));
                position.setName(resultSet.getString("position_name"));
                staff.setPosition(position);

                staffList.add(staff);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return staffList;
    }

    public static int getPositionIdByName(String positionName) {
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
}
