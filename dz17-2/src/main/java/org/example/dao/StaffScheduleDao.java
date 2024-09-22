package org.example.dao;

import org.example.database.DatabaseConnection;
import org.example.models.Staff;
import org.example.models.StaffSchedule;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StaffScheduleDao {
    private static final String ADD_SCHEDULE_SQL = "INSERT INTO staff_schedule (staff_id, work_day, start_time, end_time) VALUES (?, ?, ?, ?)";
    private static final String DELETE_SCHEDULE_SQL = "DELETE FROM staff_schedule WHERE id = ?";
    private static final String GET_ALL_SCHEDULE_SQL = "SELECT ss.*, s.first_name, s.last_name FROM staff_schedule ss JOIN staff s ON ss.staff_id = s.id";

    private static final String GET_ALL_BARISTAS_SCHEDULE_SQL =
            "SELECT ss.*, s.first_name, s.last_name FROM staff_schedule ss JOIN staff s ON ss.staff_id = s.id WHERE s.position_id = (SELECT id FROM positions WHERE name = 'Barista')";

    private static final String GET_ALL_STAFF_SCHEDULE_SQL =
            "SELECT ss.*, s.first_name, s.last_name FROM staff_schedule ss JOIN staff s ON ss.staff_id = s.id WHERE work_day BETWEEN ? AND ?";

    private static final String GET_BARISTA_SCHEDULE_SQL =
            "SELECT ss.*, s.first_name, s.last_name FROM staff_schedule ss JOIN staff s ON ss.staff_id = s.id " +
                    "WHERE s.position_id = (SELECT id FROM positions WHERE name = 'Barista' LIMIT 1) " +
                    "AND work_day BETWEEN ? AND ?";

    public static void addNewSchedule(StaffSchedule staffSchedule) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(ADD_SCHEDULE_SQL)) {
            ps.setInt(1, staffSchedule.getStaff().getId());
            ps.setDate(2, new java.sql.Date(staffSchedule.getWorkDay().getTime()));
            ps.setTime(3, staffSchedule.getStartTime());
            ps.setTime(4, staffSchedule.getEndTime());
            ps.executeUpdate();
            System.out.println("Staff schedule added successfully for: " + staffSchedule.getStaff().getFirstName() + " " + staffSchedule.getStaff().getLastName());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void deleteSchedule(int scheduleId) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SCHEDULE_SQL)) {
            preparedStatement.setInt(1, scheduleId);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.printf("Schedule with ID '%d' deleted successfully.%n", scheduleId);
            } else {
                System.out.printf("Schedule with ID '%d' not found.%n", scheduleId);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static List<StaffSchedule> getAllSchedules() {
        List<StaffSchedule> schedules = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_SCHEDULE_SQL);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                StaffSchedule schedule = new StaffSchedule();
                schedule.setId(resultSet.getInt("id"));

                Staff staff = new Staff();
                staff.setId(resultSet.getInt("staff_id"));
                staff.setFirstName(resultSet.getString("first_name"));
                staff.setLastName(resultSet.getString("last_name"));
                schedule.setStaff(staff);

                schedule.setWorkDay(resultSet.getDate("work_day"));
                schedule.setStartTime(resultSet.getTime("start_time"));
                schedule.setEndTime(resultSet.getTime("end_time"));
                schedules.add(schedule);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return schedules;
    }

    public static List<StaffSchedule> getBaristaScheduleForWeek(Date startDate) {
        List<StaffSchedule> schedules = new ArrayList<>();
        Date endDate = new Date(startDate.getTime() + (7 * 24 * 60 * 60 * 1000)); // Добавляем 7 дней

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_BARISTA_SCHEDULE_SQL)) {
            preparedStatement.setDate(1, new java.sql.Date(startDate.getTime()));
            preparedStatement.setDate(2, new java.sql.Date(endDate.getTime()));
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                StaffSchedule schedule = new StaffSchedule();
                schedule.setId(resultSet.getInt("id"));

                Staff staff = new Staff();
                staff.setId(resultSet.getInt("staff_id"));
                staff.setFirstName(resultSet.getString("first_name"));
                staff.setLastName(resultSet.getString("last_name"));
                schedule.setStaff(staff);

                schedule.setWorkDay(resultSet.getDate("work_day"));
                schedule.setStartTime(resultSet.getTime("start_time"));
                schedule.setEndTime(resultSet.getTime("end_time"));
                schedules.add(schedule);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return schedules;
    }

    public static List<StaffSchedule> getAllBaristasSchedule() {
        List<StaffSchedule> schedules = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_BARISTAS_SCHEDULE_SQL);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                StaffSchedule schedule = new StaffSchedule();
                schedule.setId(resultSet.getInt("id"));

                Staff staff = new Staff();
                staff.setId(resultSet.getInt("staff_id"));
                staff.setFirstName(resultSet.getString("first_name"));
                staff.setLastName(resultSet.getString("last_name"));
                schedule.setStaff(staff);

                schedule.setWorkDay(resultSet.getDate("work_day"));
                schedule.setStartTime(resultSet.getTime("start_time"));
                schedule.setEndTime(resultSet.getTime("end_time"));
                schedules.add(schedule);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return schedules;
    }

    public static List<StaffSchedule> getAllStaffScheduleForWeek(Date startDate) {
        List<StaffSchedule> schedules = new ArrayList<>();
        Date endDate = new Date(startDate.getTime() + (7 * 24 * 60 * 60 * 1000));

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_STAFF_SCHEDULE_SQL)) {
            preparedStatement.setDate(1, new java.sql.Date(startDate.getTime()));
            preparedStatement.setDate(2, new java.sql.Date(endDate.getTime()));
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                StaffSchedule schedule = new StaffSchedule();
                schedule.setId(resultSet.getInt("id"));

                Staff staff = new Staff();
                staff.setId(resultSet.getInt("staff_id"));
                staff.setFirstName(resultSet.getString("first_name"));
                staff.setLastName(resultSet.getString("last_name"));
                schedule.setStaff(staff);

                schedule.setWorkDay(resultSet.getDate("work_day"));
                schedule.setStartTime(resultSet.getTime("start_time"));
                schedule.setEndTime(resultSet.getTime("end_time"));
                schedules.add(schedule);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return schedules;
    }
}
