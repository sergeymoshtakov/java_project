package org.example;

import org.example.dao.StaffDao;
import org.example.database.DatabaseConnection;
import org.example.models.Position;
import org.example.models.Staff;
import org.junit.jupiter.api.*;
import org.mockito.MockedStatic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class StaffDaoTest {
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
    void updateBaristaPhone_ShouldUpdatePhone_WhenCalled() throws SQLException {
        // Arrange
        String lastName = "Doe";
        String newPhone = "987654321";

        when(mockConnection.prepareStatement(StaffDao.UPDATE_BARISTA_PHONE_SQL)).thenReturn(mockPreparedStatement);

        // Act
        StaffDao.updateBaristaPhone(lastName, newPhone);

        // Assert
        verify(mockPreparedStatement).setString(1, newPhone);
        verify(mockPreparedStatement).setString(2, lastName);
        verify(mockPreparedStatement).executeUpdate();
    }

    @Test
    void updateConfectionerAddress_ShouldUpdateEmail_WhenCalled() throws SQLException {
        // Arrange
        String confectionerName = "Doe";
        String newEmail = "new.email@example.com";

        when(mockConnection.prepareStatement(StaffDao.UPDATE_CONFECTIONER_ADDRESS)).thenReturn(mockPreparedStatement);

        // Act
        StaffDao.updateConfectionerAddress(confectionerName, newEmail);

        // Assert
        verify(mockPreparedStatement).setString(1, newEmail);
        verify(mockPreparedStatement).setString(2, confectionerName);
        verify(mockPreparedStatement).executeUpdate();
    }

    @Test
    void deleteStaff_ShouldDeleteStaff_WhenCalled() throws SQLException {
        // Arrange
        Staff staff = new Staff();
        staff.setFirstName("John");
        staff.setLastName("Doe");

        when(mockConnection.prepareStatement(StaffDao.DELETE_STAFF_SQL)).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeUpdate()).thenReturn(1);

        // Act
        StaffDao.deleteStaff(staff);

        // Assert
        verify(mockPreparedStatement).setString(1, "John");
        verify(mockPreparedStatement).setString(2, "Doe");
        verify(mockPreparedStatement).executeUpdate();
    }

    @Test
    void getAllStaff_ShouldReturnListOfStaff_WhenCalled() throws SQLException {
        // Arrange
        when(mockConnection.prepareStatement(StaffDao.GET_ALL_STAFF_SQL)).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);

        when(mockResultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        when(mockResultSet.getInt("id")).thenReturn(1).thenReturn(2);
        when(mockResultSet.getString("first_name")).thenReturn("John").thenReturn("Jane");
        when(mockResultSet.getString("last_name")).thenReturn("Doe").thenReturn("Smith");
        when(mockResultSet.getString("phone_number")).thenReturn("123456789").thenReturn("987654321");
        when(mockResultSet.getString("email")).thenReturn("john@example.com").thenReturn("jane@example.com");
        when(mockResultSet.getInt("position_id")).thenReturn(1).thenReturn(2);
        when(mockResultSet.getString("position_name")).thenReturn("Barista").thenReturn("Manager");

        // Act
        List<Staff> staffList = StaffDao.getAllStaff();

        // Assert
        assertEquals(2, staffList.size());
        assertEquals("John", staffList.get(0).getFirstName());
        assertEquals("Jane", staffList.get(1).getFirstName());
    }
}
