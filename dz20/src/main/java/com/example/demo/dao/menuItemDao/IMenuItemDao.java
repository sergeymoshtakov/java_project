package com.example.demo.dao.menuItemDao;

import com.example.demo.model.MenuItem;

import java.util.List;

public interface IMenuItemDao {
    void save(MenuItem menuItem);
    void update(MenuItem menuItem);
    void delete(int menuItemId);
    List<MenuItem> findAll();
    MenuItem findById(int menuItemId);
    void deleteAll();
}
