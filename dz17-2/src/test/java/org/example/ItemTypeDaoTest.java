package org.example;

import org.example.dao.ItemTypeDao;
import org.example.database.DatabaseConnection;
import org.example.models.ItemType;
import org.junit.jupiter.api.*;
import org.mockito.MockedStatic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ItemTypeDaoTest {
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
    void addNewItemType_ShouldInsertItemType_WhenCalled() throws SQLException {
        // Arrange
        ItemType itemType = new ItemType();
        itemType.setName("Beverage");

        when(mockConnection.prepareStatement(ItemTypeDao.ADD_ITEM_TYPE_SQL)).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeUpdate()).thenReturn(1);

        // Act
        ItemTypeDao.addNewItemType(itemType);

        // Assert
        verify(mockPreparedStatement).setString(1, "Beverage");
        verify(mockPreparedStatement).executeUpdate();
    }

    @Test
    void deleteItemType_ShouldDeleteItemType_WhenExists() throws SQLException {
        // Arrange
        ItemType itemType = new ItemType();
        itemType.setName("Beverage");

        when(mockConnection.prepareStatement(ItemTypeDao.DELETE_ITEM_TYPE_SQL)).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeUpdate()).thenReturn(1);

        // Act
        ItemTypeDao.deleteItemType(itemType);

        // Assert
        verify(mockPreparedStatement).setString(1, "Beverage");
        verify(mockPreparedStatement).executeUpdate();
    }

    @Test
    void getAllItemTypes_ShouldReturnListOfItemTypes_WhenCalled() throws SQLException {
        // Arrange
        when(mockConnection.prepareStatement(ItemTypeDao.GET_ALL_ITEM_TYPES_SQL)).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);

        when(mockResultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        when(mockResultSet.getInt("id")).thenReturn(1).thenReturn(2);
        when(mockResultSet.getString("name")).thenReturn("Beverage").thenReturn("Snack");

        // Act
        List<ItemType> itemTypes = ItemTypeDao.getAllItemTypes();

        // Assert
        assertEquals(2, itemTypes.size());
        assertEquals("Beverage", itemTypes.get(0).getName());
        assertEquals("Snack", itemTypes.get(1).getName());
    }

    @Test
    void getItemTypeById_ShouldReturnItemType_WhenExists() throws SQLException {
        // Arrange
        int itemTypeId = 1;
        when(mockConnection.prepareStatement(ItemTypeDao.GET_ITEM_TYPE_BY_ID_SQL)).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getInt("id")).thenReturn(itemTypeId);
        when(mockResultSet.getString("name")).thenReturn("Beverage");

        // Act
        ItemType itemType = ItemTypeDao.getItemTypeById(itemTypeId);

        // Assert
        assertEquals(itemTypeId, itemType.getId());
        assertEquals("Beverage", itemType.getName());
    }

    @Test
    void getItemTypeById_ShouldReturnNull_WhenNotExists() throws SQLException {
        // Arrange
        int itemTypeId = 1;
        when(mockConnection.prepareStatement(ItemTypeDao.GET_ITEM_TYPE_BY_ID_SQL)).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(false);

        // Act
        ItemType itemType = ItemTypeDao.getItemTypeById(itemTypeId);

        // Assert
        assertEquals(null, itemType);
    }
}
