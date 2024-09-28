package com.example.demo.dao.customerDao;

import com.example.demo.database.DatabaseConnection;
import com.example.demo.model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomerDao implements ICustomerDao {

    private static final String SAVE_CUSTOMER = "INSERT INTO customers (first_name, last_name, birth_date, phone_number, email, discount) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_CUSTOMER = "UPDATE customers SET first_name = ?, last_name = ?, birth_date = ?, phone_number = ?, email = ?, discount = ? WHERE id = ?";
    private static final String FIND_ALL_CUSTOMERS = "SELECT * FROM customers";
    private static final String FIND_CUSTOMER_BY_ID = "SELECT * FROM customers WHERE id = ?";
    private static final String DELETE_CUSTOMER = "DELETE FROM customers WHERE id = ?";
    private static final String DELETE_ALL_CUSTOMERS = "DELETE FROM customers";

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerDao.class);

    private static final CustomerRowMapper customerRowMapper = new CustomerRowMapper();

    @Override
    public void save(Customer customer) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_CUSTOMER)) {

            preparedStatement.setString(1, customer.getFirstName());
            preparedStatement.setString(2, customer.getLastName());
            preparedStatement.setDate(3, customer.getBirthDate());
            preparedStatement.setString(4, customer.getPhoneNumber());
            preparedStatement.setString(5, customer.getEmail());
            preparedStatement.setDouble(6, customer.getDiscount());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Error saving customer: {}", e.getMessage());
        }
    }

    @Override
    public int[] saveCustomersList(List<Customer> customers) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_CUSTOMER)) {

            for (Customer customer : customers) {
                preparedStatement.setString(1, customer.getFirstName());
                preparedStatement.setString(2, customer.getLastName());
                preparedStatement.setDate(3, customer.getBirthDate());
                preparedStatement.setString(4, customer.getPhoneNumber());
                preparedStatement.setString(5, customer.getEmail());
                preparedStatement.setDouble(6, customer.getDiscount());
                preparedStatement.addBatch();
            }

            return preparedStatement.executeBatch();
        } catch (SQLException e) {
            LOGGER.error("Error saving customer list: {}", e.getMessage());
            return new int[0];
        }
    }

    @Override
    public void update(Customer customer) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CUSTOMER)) {

            preparedStatement.setString(1, customer.getFirstName());
            preparedStatement.setString(2, customer.getLastName());
            preparedStatement.setDate(3, customer.getBirthDate());
            preparedStatement.setString(4, customer.getPhoneNumber());
            preparedStatement.setString(5, customer.getEmail());
            preparedStatement.setDouble(6, customer.getDiscount());
            preparedStatement.setInt(7, customer.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Error updating customer: {}", e.getMessage());
        }
    }

    @Override
    public void delete(int customerId) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CUSTOMER)) {

            preparedStatement.setInt(1, customerId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Error deleting customer: {}", e.getMessage());
        }
    }

    @Override
    public List<Customer> findAll() {
        List<Customer> customers = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(FIND_ALL_CUSTOMERS)) {

            int rowNum = 0;
            while (resultSet.next()) {
                Customer customer = customerRowMapper.mapRow(resultSet, rowNum++);
                customers.add(customer);
            }
        } catch (SQLException e) {
            LOGGER.error("Error finding all customers: {}", e.getMessage());
        }
        return customers;
    }

    @Override
    public Customer findById(int customerId) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_CUSTOMER_BY_ID)) {

            preparedStatement.setInt(1, customerId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return customerRowMapper.mapRow(resultSet, 0);
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Error finding customer by ID: {}", e.getMessage());
        }
        return null;
    }

    @Override
    public void deleteAll() {
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement()) {

            statement.executeUpdate(DELETE_ALL_CUSTOMERS);
        } catch (SQLException e) {
            LOGGER.error("Error deleting all customers: {}", e.getMessage());
        }
    }
}
