package com.example.demo.dao.staffScheduleDao;

import com.example.demo.database.DatabaseConnection;
import com.example.demo.model.StaffSchedule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class StaffScheduleDao implements IStaffScheduleDao {

    private static final String SAVE_STAFF_SCHEDULE = "INSERT INTO staff_schedule (staff_id, work_day, start_time, end_time) VALUES (?, ?, ?, ?)";
    private static final String UPDATE_STAFF_SCHEDULE = "UPDATE staff_schedule SET staff_id = ?, work_day = ?, start_time = ?, end_time = ? WHERE id = ?";
    private static final String FIND_ALL_STAFF_SCHEDULES = "SELECT ss.id AS schedule_id, ss.work_day, ss.start_time, ss.end_time, " +
            "s.id AS staff_id, s.first_name, s.last_name, s.phone_number, s.email, " +
            "p.id AS position_id, p.name AS position_name " +
            "FROM staff_schedule ss " +
            "LEFT JOIN staff s ON ss.staff_id = s.id " +
            "LEFT JOIN positions p ON s.position_id = p.id;";
    private static final String FIND_STAFF_SCHEDULE_BY_ID = "SELECT * FROM staff_schedule WHERE id = ?";
    private static final String DELETE_STAFF_SCHEDULE = "DELETE FROM staff_schedule WHERE id = ?";
    private static final String DELETE_ALL_STAFF_SCHEDULES = "DELETE FROM staff_schedule";

    private static final Logger LOGGER = LoggerFactory.getLogger(StaffScheduleDao.class);

    private static final StaffScheduleRowMapper staffScheduleRowMapper = new StaffScheduleRowMapper();

    @Override
    public void save(StaffSchedule staffSchedule) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_STAFF_SCHEDULE)) {

            preparedStatement.setInt(1, staffSchedule.getStaff().getId());
            preparedStatement.setDate(2, new java.sql.Date(staffSchedule.getWorkDay().getTime()));
            preparedStatement.setTime(3, staffSchedule.getStartTime());
            preparedStatement.setTime(4, staffSchedule.getEndTime());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Error saving staff schedule: {}", e.getMessage());
        }
    }

    @Override
    public void update(StaffSchedule staffSchedule) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_STAFF_SCHEDULE)) {

            preparedStatement.setInt(1, staffSchedule.getStaff().getId());
            preparedStatement.setDate(2, new java.sql.Date(staffSchedule.getWorkDay().getTime()));
            preparedStatement.setTime(3, staffSchedule.getStartTime());
            preparedStatement.setTime(4, staffSchedule.getEndTime());
            preparedStatement.setInt(5, staffSchedule.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Error updating staff schedule: {}", e.getMessage());
        }
    }

    @Override
    public void delete(int staffScheduleId) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_STAFF_SCHEDULE)) {

            preparedStatement.setInt(1, staffScheduleId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Error deleting staff schedule: {}", e.getMessage());
        }
    }

    @Override
    public List<StaffSchedule> findAll() {
        List<StaffSchedule> staffSchedules = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(FIND_ALL_STAFF_SCHEDULES)) {

            int rowNum = 0;
            while (resultSet.next()) {
                StaffSchedule staffSchedule = staffScheduleRowMapper.mapRow(resultSet, rowNum++);
                staffSchedules.add(staffSchedule);
            }
        } catch (SQLException e) {
            LOGGER.error("Error finding all staff schedules: {}", e.getMessage());
        }
        return staffSchedules;
    }

    @Override
    public StaffSchedule findById(int staffScheduleId) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_STAFF_SCHEDULE_BY_ID)) {

            preparedStatement.setInt(1, staffScheduleId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return staffScheduleRowMapper.mapRow(resultSet, 0);
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Error finding staff schedule by ID: {}", e.getMessage());
        }
        return null;
    }

    @Override
    public void deleteAll() {
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement()) {

            statement.executeUpdate(DELETE_ALL_STAFF_SCHEDULES);
        } catch (SQLException e) {
            LOGGER.error("Error deleting all staff schedules: {}", e.getMessage());
        }
    }
}
