package org.example.dao;

import org.example.database.DatabaseConnection;
import org.example.models.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDao {
    private static final String ADD_CUSTOMER_SQL = "INSERT INTO customers (first_name, last_name, birth_date, phone_number, email, discount) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_CUSTOMER_DISCOUNT_SQL = "UPDATE customers SET discount = ? WHERE last_name = ?";
    private static final String DELETE_CUSTOMER_SQL = "DELETE FROM customers WHERE first_name = ? AND last_name = ?";
    private static final String GET_ALL_CUSTOMERS_SQL = "SELECT * FROM customers";

    public static void addNewCustomer(Customer customer) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(ADD_CUSTOMER_SQL)) {
            ps.setString(1, customer.getFirstName());
            ps.setString(2, customer.getLastName());
            ps.setDate(3, Date.valueOf(customer.getBirthDate().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate()));
            ps.setString(4, customer.getPhoneNumber());
            ps.setString(5, customer.getEmail());
            ps.setDouble(6, customer.getDiscount());
            ps.executeUpdate();
            System.out.println("Customer added successfully: " + customer.getFirstName() + " " + customer.getLastName());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void updateCustomerDiscount(String lastName, double newDiscount) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CUSTOMER_DISCOUNT_SQL)) {
            preparedStatement.setDouble(1, newDiscount);
            preparedStatement.setString(2, lastName);
            preparedStatement.executeUpdate();
            System.out.printf("Customer %s discount updated successfully to %f.%n", lastName, newDiscount);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void deleteCustomer(Customer customer) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CUSTOMER_SQL)) {
            preparedStatement.setString(1, customer.getFirstName());
            preparedStatement.setString(2, customer.getLastName());
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.printf("Customer '%s %s' deleted successfully.%n", customer.getFirstName(), customer.getLastName());
            } else {
                System.out.printf("Customer '%s %s' not found.%n", customer.getFirstName(), customer.getLastName());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_CUSTOMERS_SQL);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Customer customer = new Customer();
                customer.setId(resultSet.getInt("id"));
                customer.setFirstName(resultSet.getString("first_name"));
                customer.setLastName(resultSet.getString("last_name"));
                customer.setBirthDate(resultSet.getDate("birth_date"));
                customer.setPhoneNumber(resultSet.getString("phone_number"));
                customer.setEmail(resultSet.getString("email"));
                customer.setDiscount(resultSet.getDouble("discount"));
                customers.add(customer);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return customers;
    }
}
