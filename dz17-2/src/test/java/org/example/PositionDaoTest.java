package org.example;

import org.example.dao.PositionDao;
import org.example.database.DatabaseConnection;
import org.example.models.Position;
import org.junit.jupiter.api.*;
import org.mockito.MockedStatic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PositionDaoTest {

    private Connection mockConnection;
    private PreparedStatement mockPreparedStatement;
    private ResultSet mockResultSet;

    private MockedStatic<DatabaseConnection> mockDatabaseConnection;

    @BeforeEach
    void setUp() throws SQLException {
        mockConnection = mock(Connection.class);
        mockPreparedStatement = mock(PreparedStatement.class);
        mockResultSet = mock(ResultSet.class);

        // Mock DatabaseConnection to return the mocked connection
        mockDatabaseConnection = mockStatic(DatabaseConnection.class);
        mockDatabaseConnection.when(DatabaseConnection::getConnection).thenReturn(mockConnection);
    }

    @AfterEach
    void tearDown() throws SQLException {
        // Close resources
        mockResultSet.close();
        mockPreparedStatement.close();
        mockConnection.close();

        // Close the static mock
        mockDatabaseConnection.close();
    }

    @Test
    void addNewPosition_ShouldInsertPosition_WhenCalled() throws SQLException {
        // Arrange
        Position position = new Position();
        position.setName("Developer");

        when(mockConnection.prepareStatement(PositionDao.ADD_POSITION_SQL)).thenReturn(mockPreparedStatement);

        // Act
        PositionDao.addNewPosition(position);

        // Assert
        verify(mockPreparedStatement).setString(1, "Developer");
        verify(mockPreparedStatement).executeUpdate();
    }

    @Test
    void deletePosition_ShouldDeletePosition_WhenCalled() throws SQLException {
        // Arrange
        Position position = new Position();
        position.setName("Manager");

        when(mockConnection.prepareStatement(PositionDao.DELETE_POSITION_SQL)).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeUpdate()).thenReturn(1); // Simulate successful deletion

        // Act
        PositionDao.deletePosition(position);

        // Assert
        verify(mockPreparedStatement).setString(1, "Manager");
        verify(mockPreparedStatement).executeUpdate();
    }

    @Test
    void deletePosition_ShouldNotDeletePosition_WhenNotFound() throws SQLException {
        // Arrange
        Position position = new Position();
        position.setName("NonExistent");

        when(mockConnection.prepareStatement(PositionDao.DELETE_POSITION_SQL)).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeUpdate()).thenReturn(0); // Simulate no rows affected

        // Act
        PositionDao.deletePosition(position);

        // Assert
        verify(mockPreparedStatement).setString(1, "NonExistent");
        verify(mockPreparedStatement).executeUpdate();
    }

    @Test
    void getAllPositions_ShouldReturnListOfPositions_WhenCalled() throws SQLException {
        // Arrange
        when(mockConnection.prepareStatement(PositionDao.GET_ALL_POSITIONS_SQL)).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);

        // Mock the ResultSet to return two positions
        when(mockResultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        when(mockResultSet.getInt("id")).thenReturn(1).thenReturn(2);
        when(mockResultSet.getString("name")).thenReturn("Developer", "Manager");

        // Act
        List<Position> positions = PositionDao.getAllPositions();

        // Assert
        assertEquals(2, positions.size());
        assertEquals("Developer", positions.get(0).getName());
        assertEquals("Manager", positions.get(1).getName());
    }
}
