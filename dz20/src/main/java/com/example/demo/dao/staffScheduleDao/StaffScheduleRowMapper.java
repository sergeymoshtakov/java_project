package com.example.demo.dao.staffScheduleDao;

import com.example.demo.model.Position;
import com.example.demo.model.Staff;
import com.example.demo.model.StaffSchedule;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StaffScheduleRowMapper implements RowMapper<StaffSchedule> {

    @Override
    public StaffSchedule mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Position position = new Position(
                resultSet.getInt("position_id"),
                resultSet.getString("position_name")
        );

        Staff staff = new Staff(
                resultSet.getInt("staff_id"),
                resultSet.getString("first_name"),
                resultSet.getString("last_name"),
                resultSet.getString("phone_number"),
                resultSet.getString("email"),
                position
        );

        return new StaffSchedule(
                resultSet.getInt("schedule_id"),
                staff,
                resultSet.getDate("work_day"),
                resultSet.getTime("start_time"),
                resultSet.getTime("end_time")
        );
    }
}
