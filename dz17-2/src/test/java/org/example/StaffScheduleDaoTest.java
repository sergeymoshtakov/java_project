package org.example;

import org.example.dao.StaffScheduleDao;
import org.example.database.DatabaseConnection;
import org.example.models.Staff;
import org.example.models.StaffSchedule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.sql.*;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class StaffScheduleDaoTest {

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
    void addNewSchedule_ShouldInsertSchedule_WhenCalled() throws SQLException {
        // Arrange
        Staff staff = new Staff();
        staff.setId(1);
        staff.setFirstName("John");
        staff.setLastName("Doe");

        StaffSchedule staffSchedule = new StaffSchedule();
        staffSchedule.setStaff(staff);
        staffSchedule.setWorkDay(new Date());
        staffSchedule.setStartTime(Time.valueOf("09:00:00"));
        staffSchedule.setEndTime(Time.valueOf("17:00:00"));

        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);

        // Act
        StaffScheduleDao.addNewSchedule(staffSchedule);

        // Assert
        verify(mockPreparedStatement).setInt(1, staff.getId());
        verify(mockPreparedStatement).setDate(2, new java.sql.Date(staffSchedule.getWorkDay().getTime()));
        verify(mockPreparedStatement).setTime(3, staffSchedule.getStartTime());
        verify(mockPreparedStatement).setTime(4, staffSchedule.getEndTime());
        verify(mockPreparedStatement).executeUpdate();
    }

    @Test
    void deleteSchedule_ShouldDeleteSchedule_WhenScheduleExists() throws SQLException {
        // Arrange
        int scheduleId = 1;

        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeUpdate()).thenReturn(1);

        // Act
        StaffScheduleDao.deleteSchedule(scheduleId);

        // Assert
        verify(mockPreparedStatement).setInt(1, scheduleId);
        verify(mockPreparedStatement).executeUpdate();
    }

    @Test
    void getAllSchedules_ShouldReturnListOfSchedules_WhenSchedulesExist() throws SQLException {
        // Arrange
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);

        when(mockResultSet.next()).thenReturn(true).thenReturn(false);
        when(mockResultSet.getInt("id")).thenReturn(1);
        when(mockResultSet.getInt("staff_id")).thenReturn(1);
        when(mockResultSet.getString("first_name")).thenReturn("John");
        when(mockResultSet.getString("last_name")).thenReturn("Doe");
        when(mockResultSet.getDate("work_day")).thenReturn(new java.sql.Date(System.currentTimeMillis()));
        when(mockResultSet.getTime("start_time")).thenReturn(Time.valueOf("09:00:00"));
        when(mockResultSet.getTime("end_time")).thenReturn(Time.valueOf("17:00:00"));

        // Act
        List<StaffSchedule> schedules = StaffScheduleDao.getAllSchedules();

        // Assert
        assertNotNull(schedules);
        assertEquals(1, schedules.size());
        assertEquals("John", schedules.get(0).getStaff().getFirstName());
        assertEquals("Doe", schedules.get(0).getStaff().getLastName());
    }
}
