package org.example;

import org.example.dao.MenuItemDao;
import org.example.dao.OrderDao;
import org.example.dao.OrderItemDao;
import org.example.database.DatabaseConnection;
import org.example.models.Order;
import org.example.models.OrderItem;
import org.example.models.MenuItem;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.sql.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class OrderItemDaoTest {

    private Connection mockConnection;
    private PreparedStatement mockPreparedStatement;
    private ResultSet mockResultSet;
    private MockedStatic<DatabaseConnection> mockedStaticDatabaseConnection;

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
    void addNewOrderItem_ShouldInsertOrderItem_WhenCalled() throws SQLException {
        // Arrange
        String menuItemName = "Pizza";
        int orderId = 1;
        int quantity = 2;
        double price = 10.0;

        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getInt("id")).thenReturn(1); // menuItemId

        when(mockPreparedStatement.executeUpdate()).thenReturn(1);

        // Act
        OrderItemDao.addNewOrderItem(orderId, menuItemName, quantity, price);

        // Assert
        verify(mockPreparedStatement, times(1)).setInt(1, orderId);
        verify(mockPreparedStatement, times(1)).setInt(2, 1); // Проверяем что menuItemId был правильно установлен
        verify(mockPreparedStatement, times(1)).setInt(3, quantity);
        verify(mockPreparedStatement, times(1)).setDouble(4, price);
        verify(mockPreparedStatement).executeUpdate();
    }

    @Test
    void deleteOrderItem_ShouldDeleteOrderItem_WhenOrderItemExists() throws SQLException {
        // Arrange
        int orderItemId = 1;

        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeUpdate()).thenReturn(1);

        // Act
        OrderItemDao.deleteOrderItem(orderItemId);

        // Assert
        verify(mockPreparedStatement).setInt(1, orderItemId);
        verify(mockPreparedStatement).executeUpdate();
    }

    @Test
    void getMenuItemIdByName_ShouldReturnMenuItemId_WhenMenuItemExists() throws SQLException {
        // Arrange
        String menuItemName = "Pizza";

        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getInt("id")).thenReturn(1);

        // Act
        int menuItemId = OrderItemDao.getMenuItemIdByName(menuItemName);

        // Assert
        assertEquals(1, menuItemId);
        verify(mockPreparedStatement).setString(1, menuItemName);
        verify(mockPreparedStatement).executeQuery();
    }

    @Test
    void getMenuItemById_ShouldReturnMenuItem_WhenMenuItemExists() throws SQLException {
        // Arrange
        int menuItemId = 1;

        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getInt("id")).thenReturn(menuItemId);
        when(mockResultSet.getString("name_en")).thenReturn("Pizza");
        when(mockResultSet.getString("name_de")).thenReturn("Pizza");
        when(mockResultSet.getDouble("price")).thenReturn(10.0);

        // Act
        MenuItem menuItem = OrderItemDao.getMenuItemById(menuItemId);

        // Assert
        assertNotNull(menuItem);
        assertEquals("Pizza", menuItem.getNameEn());
        assertEquals(10.0, menuItem.getPrice());
    }
}
