package org.example.coffeeshop;

import org.example.coffeeshop.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TaskFour {public static final String DELETE_DESSERT_SQL = "DELETE FROM desserts WHERE name_en = ?";
    public static final String DELETE_WAITER_SQL = "DELETE FROM staff WHERE full_name = ? AND position = 'Официант'";
    public static final String DELETE_BARISTA_SQL = "DELETE FROM staff WHERE full_name = ? AND position = 'Бариста'";
    public static final String DELETE_CUSTOMER_SQL = "DELETE FROM customers WHERE full_name = ?";

    public static void main(String[] args) {
        deleteDessert("Cheesecake");
        deleteWaiter("Smith");
        deleteBarista("Johns");
        deleteCustomer("Jane Doe");
    }

    public static void deleteDessert(String dessertName) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_DESSERT_SQL)) {
            preparedStatement.setString(1, dessertName);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.printf("Dessert '%s' deleted successfully.%n", dessertName);
            } else {
                System.out.printf("Dessert '%s' not found.%n", dessertName);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void deleteWaiter(String waiterName) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_WAITER_SQL)) {
            preparedStatement.setString(1, waiterName);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.printf("Waiter '%s' deleted successfully.%n", waiterName);
            } else {
                System.out.printf("Waiter '%s' not found.%n", waiterName);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void deleteBarista(String baristaName) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BARISTA_SQL)) {
            preparedStatement.setString(1, baristaName);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.printf("Barista '%s' deleted successfully.%n", baristaName);
            } else {
                System.out.printf("Barista '%s' not found.%n", baristaName);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void deleteCustomer(String customerName) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CUSTOMER_SQL)) {
            preparedStatement.setString(1, customerName);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.printf("Customer '%s' deleted successfully.%n", customerName);
            } else {
                System.out.printf("Customer '%s' not found.%n", customerName);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
