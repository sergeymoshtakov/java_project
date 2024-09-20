package org.example.coffeeshop;

import org.example.coffeeshop.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TaskThree {
    public static String UPDATE_DRINK_PRICE = "UPDATE drinks SET price = ? WHERE name_en = ?";
    public static String UPDATE_CONFECTIONER_ADDRESS = "UPDATE staff SET email = ? WHERE full_name = ? AND position = 'Кондитер'";
    public static String UPDATE_BARISTA_PHONE = "UPDATE staff SET phone_number = ? WHERE full_name = ? AND position = 'Бариста'";
    public static String UPDATE_CUSTOMER_DISCOUNT = "UPDATE customers SET discount = ? WHERE full_name = ?";

    public static void main( String[] args ){
        updateDrinkPrice("Coffee", 4.99);
        updateConfectionerAddress("Johns", "newemale@gmail.com");
        updateBaristaPhone("Stone", "77777777777");
        updateCustomerDiscount("Jane Doe", 15.0);
    }

    public static void updateDrinkPrice(String drink, double price) {
        try (Connection connection = DatabaseConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_DRINK_PRICE)) {
            preparedStatement.setDouble(1, price);
            preparedStatement.setString(2, drink);
            preparedStatement.executeUpdate();
            System.out.printf("Price of %s updated successfully to %f%n", drink, price);
        } catch (SQLException e){
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

    public static void updateBaristaPhone(String baristaName, String newPhone){
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BARISTA_PHONE)) {
            preparedStatement.setString(1, newPhone);
            preparedStatement.setString(2, baristaName);
            preparedStatement.executeUpdate();
            System.out.printf("Barista %s phone updated successfully to %s.%n", baristaName, newPhone);
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public static void updateCustomerDiscount(String customerName, double newDiscount){
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CUSTOMER_DISCOUNT)) {
            preparedStatement.setDouble(1, newDiscount);
            preparedStatement.setString(2, customerName);
            preparedStatement.executeUpdate();
            System.out.printf("Customer %s discount updated successfully to %f.%n", customerName, newDiscount);
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}