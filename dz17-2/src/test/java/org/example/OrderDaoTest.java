package org.example;

import org.example.dao.OrderDao;
import org.example.models.Order;
import org.example.database.DatabaseConnection;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class OrderDaoTest {

    private Connection mockConnection;
    private PreparedStatement mockPreparedStatement;
    private ResultSet mockResultSet;
    private MockedStatic<DatabaseConnection> mockedStaticDatabaseConnection; // Для хранения статического мока

    @BeforeEach
    void setUp() throws SQLException {
        mockConnection = mock(Connection.class);
        mockPreparedStatement = mock(PreparedStatement.class);
        mockResultSet = mock(ResultSet.class);

        mockedStaticDatabaseConnection = mockStatic(DatabaseConnection.class);
        when(DatabaseConnection.getConnection()).thenReturn(mockConnection);
    }

    @AfterEach
    void tearDown() {
        mockedStaticDatabaseConnection.close();
    }

    @Test
    void addNewOrder_ShouldInsertOrder_WhenCalled() throws SQLException {
        // Arrange
        Order order = new Order();
        order.setCustomerFirstName("John");
        order.setCustomerLastName("Doe");
        order.setOrderDate(Timestamp.valueOf(LocalDateTime.of(2023, 9, 1, 10, 0)));
        order.setTotalAmount(100.0);

        // Устанавливаем поведение mockConnection и mockPreparedStatement
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeUpdate()).thenReturn(1);

        // Act
        OrderDao.addNewOrder(order);

        // Assert
        verify(mockPreparedStatement).setString(1, "John");
        verify(mockPreparedStatement).setString(2, "Doe");
        verify(mockPreparedStatement).setTimestamp(3, order.getOrderDate());
        verify(mockPreparedStatement).setDouble(4, 100.0);
        verify(mockPreparedStatement).executeUpdate();
    }

    @Test
    void deleteOrder_ShouldDeleteOrder_WhenOrderIdExists() throws SQLException {
        // Arrange
        int orderId = 1;

        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeUpdate()).thenReturn(1);

        // Act
        OrderDao.deleteOrder(orderId);

        // Assert
        verify(mockPreparedStatement).setInt(1, orderId);
        verify(mockPreparedStatement).executeUpdate();
    }

    @Test
    void getAllOrders_ShouldReturnListOfOrders_WhenOrdersExist() throws SQLException {
        // Arrange
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);

        when(mockResultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false); // Mock два заказа
        when(mockResultSet.getInt("id")).thenReturn(1).thenReturn(2);
        when(mockResultSet.getString("customer_first_name")).thenReturn("John").thenReturn("Jane");
        when(mockResultSet.getString("customer_last_name")).thenReturn("Doe").thenReturn("Smith");
        when(mockResultSet.getTimestamp("order_date")).thenReturn(Timestamp.valueOf(LocalDateTime.of(2023, 9, 1, 10, 0)));
        when(mockResultSet.getDouble("total_amount")).thenReturn(100.0).thenReturn(200.0);

        // Act
        List<Order> orders = OrderDao.getAllOrders();

        // Assert
        assertEquals(2, orders.size());
        assertEquals("John", orders.get(0).getCustomerFirstName());
        assertEquals("Jane", orders.get(1).getCustomerFirstName());
    }

    @Test
    void getOrderById_ShouldReturnOrder_WhenOrderIdExists() throws SQLException {
        // Arrange
        int orderId = 1;

        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true);

        when(mockResultSet.getInt("id")).thenReturn(orderId);
        when(mockResultSet.getString("customer_first_name")).thenReturn("John");
        when(mockResultSet.getString("customer_last_name")).thenReturn("Doe");
        when(mockResultSet.getTimestamp("order_date")).thenReturn(Timestamp.valueOf(LocalDateTime.of(2023, 9, 1, 10, 0)));
        when(mockResultSet.getDouble("total_amount")).thenReturn(100.0);

        // Act
        Order order = OrderDao.getOrderById(orderId);

        // Assert
        assertNotNull(order);
        assertEquals(orderId, order.getId());
        assertEquals("John", order.getCustomerFirstName());
        assertEquals(100.0, order.getTotalAmount());
    }
}
