package com.example.demo.dao.staffDao;

import com.example.demo.model.Staff;

import java.util.List;

public interface IStaffDao {
    void save(Staff staff);
    void update(Staff staff);
    void delete(int staffId);
    List<Staff> findAll();
    Staff findById(int staffId);
    void deleteAll();
}
