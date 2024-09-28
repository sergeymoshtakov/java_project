package com.example.demo.dao.positionDao;

import com.example.demo.model.Position;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PositionRowMapper implements RowMapper<Position> {
    @Override
    public Position mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        return new Position(
                resultSet.getInt("id"),
                resultSet.getString("name")
        );
    }
}
