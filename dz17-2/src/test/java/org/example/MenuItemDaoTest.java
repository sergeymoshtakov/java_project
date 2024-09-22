package org.example;

import org.example.dao.MenuItemDao;
import org.example.database.DatabaseConnection;
import org.example.models.ItemType;
import org.example.models.MenuItem;
import org.junit.jupiter.api.*;
import org.mockito.MockedStatic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class MenuItemDaoTest {
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
    void addNewMenuItem_ShouldNotInsert_WhenItemTypeNotFound() throws SQLException {
        // Arrange
        MenuItem menuItem = new MenuItem();
        menuItem.setNameEn("Coffee");
        menuItem.setItemType(new ItemType());
        menuItem.getItemType().setName("NonExistentType");

        // Mock the prepared statement for the first method call
        when(mockConnection.prepareStatement(MenuItemDao.ADD_MENU_ITEM_SQL)).thenReturn(mockPreparedStatement);

        // Mock the prepared statement for getItemTypeIdByName to return -1
        PreparedStatement mockGetItemTypeIdStatement = mock(PreparedStatement.class);
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockGetItemTypeIdStatement);
        when(mockGetItemTypeIdStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(false);

        // Act
        MenuItemDao.addNewMenuItem(menuItem);

        // Assert
        verify(mockPreparedStatement, never()).executeUpdate();
    }

    @Test
    void updateMenuItemPrice_ShouldUpdatePrice_WhenCalled() throws SQLException {
        // Arrange
        String menuItemName = "Coffee";
        double newPrice = 4.00;

        when(mockConnection.prepareStatement(MenuItemDao.UPDATE_MENU_ITEM_PRICE_SQL)).thenReturn(mockPreparedStatement);

        // Act
        MenuItemDao.updateMenuItemPrice(menuItemName, newPrice);

        // Assert
        verify(mockPreparedStatement).setDouble(1, newPrice);
        verify(mockPreparedStatement).setString(2, menuItemName);
        verify(mockPreparedStatement).executeUpdate();
    }

    @Test
    void deleteMenuItem_ShouldDeleteMenuItem_WhenExists() throws SQLException {
        // Arrange
        MenuItem menuItem = new MenuItem();
        menuItem.setNameEn("Coffee");

        when(mockConnection.prepareStatement(MenuItemDao.DELETE_MENU_ITEM_SQL)).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeUpdate()).thenReturn(1);

        // Act
        MenuItemDao.deleteMenuItem(menuItem);

        // Assert
        verify(mockPreparedStatement).setString(1, "Coffee");
        verify(mockPreparedStatement).executeUpdate();
    }

    @Test
    void getAllMenuItems_ShouldReturnListOfMenuItems_WhenCalled() throws SQLException {
        // Arrange
        when(mockConnection.prepareStatement(MenuItemDao.GET_ALL_MENU_ITEMS_SQL)).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);

        when(mockResultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        when(mockResultSet.getInt("id")).thenReturn(1).thenReturn(2);
        when(mockResultSet.getString("name_en")).thenReturn("Coffee").thenReturn("Tea");
        when(mockResultSet.getString("name_de")).thenReturn("Kaffee").thenReturn("Tee");
        when(mockResultSet.getDouble("price")).thenReturn(3.50).thenReturn(2.50);
        when(mockResultSet.getInt("item_type_id")).thenReturn(1).thenReturn(2);
        when(mockResultSet.getString("item_type_name")).thenReturn("Beverage").thenReturn("Beverage");

        // Act
        List<MenuItem> menuItems = MenuItemDao.getAllMenuItems();

        // Assert
        assertEquals(2, menuItems.size());
        assertEquals("Coffee", menuItems.get(0).getNameEn());
        assertEquals("Tea", menuItems.get(1).getNameEn());
    }

    @Test
    void getItemTypeIdByName_ShouldReturnId_WhenExists() throws SQLException {
        // Arrange
        String itemTypeName = "Beverage";
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getInt("id")).thenReturn(1);

        // Act
        int id = MenuItemDao.getItemTypeIdByName(itemTypeName);

        // Assert
        assertEquals(1, id);
    }

    @Test
    void getItemTypeIdByName_ShouldReturnMinusOne_WhenNotExists() throws SQLException {
        // Arrange
        String itemTypeName = "NonExistentType";
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(false);

        // Act
        int id = MenuItemDao.getItemTypeIdByName(itemTypeName);

        // Assert
        assertEquals(-1, id);
    }
}
