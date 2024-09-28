package com.example.demo.dao.ItemTypeDao;

import com.example.demo.model.ItemType;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemTypeRowMapper implements RowMapper<ItemType> {
    @Override
    public ItemType mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        return new ItemType(
                resultSet.getInt("id"),
                resultSet.getString("name")
        );
    }
}
