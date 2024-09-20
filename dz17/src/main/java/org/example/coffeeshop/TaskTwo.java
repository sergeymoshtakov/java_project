package org.example.coffeeshop;

import org.example.coffeeshop.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TaskTwo {
    private static final String ADD_DRINK_SQL = "INSERT INTO drinks (name_en, name_de, price) VALUES (?, ?, ?)";
    private static final String ADD_DESSERT_SQL = "INSERT INTO desserts (name_en, name_de, price) VALUES (?, ?, ?)";
    private static final String ADD_STAFF_SQL = "INSERT INTO staff (full_name, phone_number, email, position) VALUES (?, ?, ?, ?)";
    private static final String ADD_CUSTOMER_SQL = "INSERT INTO customers (full_name, birth_date, phone_number, email, discount) VALUES (?, ?, ?, ?, ?)";

    public static void main( String[] args ) {
        addNewDrink("Coffee", "Cafe", 20.0);
        addNewDessert("Cheesecake", "Käsekuchen", 40.0);
        addNewBarista("Johns", "1234567890", "johns@gmail.com");
        addNewConfectioner("Stone", "1234567890", "stone@gmail.com");
        addNewCustomer("Jane Doe", "1990-05-15", "5551234567", "jane.doe@gmail.com", 10.0);
        addNewStaff("Smith", "100000", "smith@gmail.com", "Официант");
    }

    public static void addNewDrink(String nameEn, String nameDe, double price) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(ADD_DRINK_SQL)) {
            ps.setString(1, nameEn);
            ps.setString(2, nameDe);
            ps.setDouble(3, price);
            ps.executeUpdate();
            System.out.println("Drink added successfully: " + nameEn);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void addNewDessert(String nameEn, String nameDe, double price) {
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(ADD_DESSERT_SQL)){
            ps.setString(1, nameEn);
            ps.setString(2, nameDe);
            ps.setDouble(3, price);
            ps.executeUpdate();
            System.out.println("Dessert added successfully: " + nameEn);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void addNewStaff(String fullName, String phoneNumber, String email, String position) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(ADD_STAFF_SQL)) {
            ps.setString(1, fullName);
            ps.setString(2, phoneNumber);
            ps.setString(3, email);
            ps.setString(4, position);
            ps.executeUpdate();
            System.out.println(position + " added successfully: " + fullName);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void addNewBarista(String fullName, String phoneNumber, String email) {
        addNewStaff(fullName, phoneNumber, email, "Бариста");
    }

    public static void addNewConfectioner(String fullName, String phoneNumber, String email) {
        addNewStaff(fullName, phoneNumber, email, "Кондитер");
    }

    public static void addNewCustomer(String fullName, String birthDate, String phoneNumber, String email, double discount) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(ADD_CUSTOMER_SQL)) {
            ps.setString(1, fullName);
            ps.setDate(2, Date.valueOf(birthDate));
            ps.setString(3, phoneNumber);
            ps.setString(4, email);
            ps.setDouble(5, discount);
            ps.executeUpdate();
            System.out.println("Customer added successfully: " + fullName);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
