package org.example.coffeeshop;

import org.example.coffeeshop.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TaskFive {
    public static final String SELECT_ALL_DRINKS = "SELECT * FROM drinks";
    public static final String SELECT_ALL_DESSERTS = "SELECT * FROM desserts";
    public static final String SELECT_ALL_BARISTAS = "SELECT * FROM staff WHERE position = 'Бариста'";
    public static final String SELECT_ALL_WAITERS = "SELECT * FROM staff WHERE position = 'Официант'";

    public static void main(String[] args){
        showAllDrinks();
        showAllDesserts();
        showAllBaristas();
        showAllWaiters();
    }

    public static void showAllDrinks() {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_DRINKS);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            System.out.println("Drinks available:");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nameEn = resultSet.getString("name_en");
                String nameDe = resultSet.getString("name_de");
                double price = resultSet.getDouble("price");
                System.out.printf("ID: %d, Name (EN): %s, Name (DE): %s, Price: %.2f%n", id, nameEn, nameDe, price);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void showAllDesserts() {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_DESSERTS);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            System.out.println("Desserts available:");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nameEn = resultSet.getString("name_en");
                String nameDe = resultSet.getString("name_de");
                double price = resultSet.getDouble("price");
                System.out.printf("ID: %d, Name (EN): %s, Name (DE): %s, Price: %.2f%n", id, nameEn, nameDe, price);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void showAllBaristas() {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_BARISTAS);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            System.out.println("Baristas:");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String fullName = resultSet.getString("full_name");
                String phone = resultSet.getString("phone_number");
                String email = resultSet.getString("email");
                System.out.printf("ID: %d, Name: %s, Phone: %s, Email: %s%n", id, fullName, phone, email);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void showAllWaiters() {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_WAITERS);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            System.out.println("Waiters:");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String fullName = resultSet.getString("full_name");
                String phone = resultSet.getString("phone_number");
                String email = resultSet.getString("email");
                System.out.printf("ID: %d, Name: %s, Phone: %s, Email: %s%n", id, fullName, phone, email);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
