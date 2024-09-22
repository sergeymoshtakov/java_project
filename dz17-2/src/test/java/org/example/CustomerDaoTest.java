package org.example;

import org.example.dao.CustomerDao;
import org.example.database.DatabaseConnection;
import org.example.models.Customer;
import org.junit.jupiter.api.*;
import org.mockito.MockedStatic;

import java.sql.*;
import java.time.ZoneId;
import java.util.List;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomerDaoTest {
    private Connection mockConnection;
    private PreparedStatement mockPreparedStatement;
    private ResultSet mockResultSet;

    private MockedStatic<DatabaseConnection> mockDatabaseConnection;

    @BeforeEach
    void setUp() throws SQLException {
        mockConnection = mock(Connection.class);
        mockPreparedStatement = mock(PreparedStatement.class);
        mockResultSet = mock(ResultSet.class);

        mockDatabaseConnection = mockStatic(DatabaseConnection.class);
        mockDatabaseConnection.when(DatabaseConnection::getConnection).thenReturn(mockConnection);
    }

    @AfterEach
    void tearDown() throws SQLException {
        mockDatabaseConnection.close();
    }

    @Test
    void addNewCustomer_ShouldInsertCustomer_WhenCalled() throws SQLException {
        // Arrange
        Customer customer = new Customer();
        customer.setFirstName("John");
        customer.setLastName("Doe");

        // Преобразование LocalDate в java.util.Date
        LocalDate localDate = LocalDate.of(1990, 1, 1);
        java.util.Date birthDate = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        customer.setBirthDate(birthDate);  // Использование java.util.Date

        customer.setPhoneNumber("1234567890");
        customer.setEmail("john.doe@example.com");
        customer.setDiscount(10.0);

        // Mock PreparedStatement
        when(mockConnection.prepareStatement(CustomerDao.ADD_CUSTOMER_SQL)).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeUpdate()).thenReturn(1);

        // Act
        CustomerDao.addNewCustomer(customer);

        // Assert
        verify(mockPreparedStatement).setString(1, "John");
        verify(mockPreparedStatement).setString(2, "Doe");
        verify(mockPreparedStatement).setDate(3, new java.sql.Date(birthDate.getTime())); // Преобразование в java.sql.Date
        verify(mockPreparedStatement).setString(4, "1234567890");
        verify(mockPreparedStatement).setString(5, "john.doe@example.com");
        verify(mockPreparedStatement).setDouble(6, 10.0);
        verify(mockPreparedStatement).executeUpdate();
    }

    @Test
    void updateCustomerDiscount_ShouldUpdateDiscount_WhenCalled() throws SQLException {
        // Arrange
        String lastName = "Doe";
        double newDiscount = 15.0;

        when(mockConnection.prepareStatement(CustomerDao.UPDATE_CUSTOMER_DISCOUNT_SQL)).thenReturn(mockPreparedStatement);

        // Act
        CustomerDao.updateCustomerDiscount(lastName, newDiscount);

        // Assert
        verify(mockPreparedStatement).setDouble(1, newDiscount);
        verify(mockPreparedStatement).setString(2, lastName);
        verify(mockPreparedStatement).executeUpdate();
    }

    @Test
    void deleteCustomer_ShouldDeleteCustomer_WhenExists() throws SQLException {
        // Arrange
        Customer customer = new Customer();
        customer.setFirstName("John");
        customer.setLastName("Doe");

        when(mockConnection.prepareStatement(CustomerDao.DELETE_CUSTOMER_SQL)).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeUpdate()).thenReturn(1);

        // Act
        CustomerDao.deleteCustomer(customer);

        // Assert
        verify(mockPreparedStatement).setString(1, "John");
        verify(mockPreparedStatement).setString(2, "Doe");
        verify(mockPreparedStatement).executeUpdate();
    }

    @Test
    void getAllCustomers_ShouldReturnListOfCustomers_WhenCalled() throws SQLException {
        // Arrange
        when(mockConnection.prepareStatement(CustomerDao.GET_ALL_CUSTOMERS_SQL)).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);

        when(mockResultSet.getInt("id")).thenReturn(1).thenReturn(2);
        when(mockResultSet.getString("first_name")).thenReturn("John").thenReturn("Jane");
        when(mockResultSet.getString("last_name")).thenReturn("Doe").thenReturn("Smith");
        when(mockResultSet.getDate("birth_date")).thenReturn(Date.valueOf(LocalDate.of(1900, 1, 1))).thenReturn(Date.valueOf("1992-02-02"));
        when(mockResultSet.getString("phone_number")).thenReturn("1234567890").thenReturn("0987654321");
        when(mockResultSet.getString("email")).thenReturn("john.doe@example.com").thenReturn("jane.smith@example.com");
        when(mockResultSet.getDouble("discount")).thenReturn(10.0).thenReturn(20.0);

        // Act
        List<Customer> customers = CustomerDao.getAllCustomers();

        // Assert
        assertEquals(2, customers.size());
        assertEquals("John", customers.get(0).getFirstName());
        assertEquals("Jane", customers.get(1).getFirstName());
    }

    @Test
    void getCustomerByLastName_ShouldReturnCustomer_WhenExists() throws SQLException {
        // Arrange
        String lastName = "Doe";
        when(mockConnection.prepareStatement(CustomerDao.GET_CUSTOMER_BY_LAST_NAME_SQL)).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true);

        when(mockResultSet.getInt("id")).thenReturn(1);
        when(mockResultSet.getString("first_name")).thenReturn("John");
        when(mockResultSet.getString("last_name")).thenReturn("Doe");
        when(mockResultSet.getDate("birth_date")).thenReturn(Date.valueOf("1990-01-01"));
        when(mockResultSet.getString("phone_number")).thenReturn("1234567890");
        when(mockResultSet.getString("email")).thenReturn("john.doe@example.com");
        when(mockResultSet.getDouble("discount")).thenReturn(10.0);

        // Act
        Customer customer = CustomerDao.getCustomerByLastName(lastName);

        // Assert
        assertNotNull(customer);
        assertEquals("John", customer.getFirstName());
        assertEquals("Doe", customer.getLastName());
    }

    @Test
    void getCustomerByLastName_ShouldReturnNull_WhenNotExists() throws SQLException {
        // Arrange
        String lastName = "NonExistent";
        when(mockConnection.prepareStatement(CustomerDao.GET_CUSTOMER_BY_LAST_NAME_SQL)).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(false);

        // Act
        Customer customer = CustomerDao.getCustomerByLastName(lastName);

        // Assert
        assertNull(customer);
    }
}
