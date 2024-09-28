package com.example.demo.dao.menuItemDao;

import com.example.demo.model.ItemType;
import com.example.demo.model.MenuItem;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MenuItemRowMapper implements RowMapper<MenuItem> {
    @Override
    public MenuItem mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        ItemType itemType = new ItemType(
                resultSet.getInt("item_type_id"),
                resultSet.getString("item_type_name")
        );

        return new MenuItem(
                resultSet.getInt("id"),
                resultSet.getString("name_en"),
                resultSet.getString("name_de"),
                resultSet.getDouble("price"),
                itemType
        );
    }
}
