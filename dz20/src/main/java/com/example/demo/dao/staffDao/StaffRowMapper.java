package com.example.demo.dao.staffDao;

import com.example.demo.dao.positionDao.PositionRowMapper;
import com.example.demo.model.Position;
import com.example.demo.model.Staff;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StaffRowMapper implements RowMapper<Staff> {
    @Override
    public Staff mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        return new Staff(
                resultSet.getInt("id"),
                resultSet.getString("first_name"),
                resultSet.getString("last_name"),
                resultSet.getString("phone_number"),
                resultSet.getString("email"),
                new Position(
                        resultSet.getInt("position_id"),
                        resultSet.getString("position_name")
                )
        );
    }
}
