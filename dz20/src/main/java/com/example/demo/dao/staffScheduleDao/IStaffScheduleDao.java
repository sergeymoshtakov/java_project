package com.example.demo.dao.staffScheduleDao;

import com.example.demo.model.StaffSchedule;

import java.util.List;

public interface IStaffScheduleDao {
    void save(StaffSchedule staffSchedule);
    void update(StaffSchedule staffSchedule);
    void delete(int staffScheduleId);
    List<StaffSchedule> findAll();
    StaffSchedule findById(int staffScheduleId);
    void deleteAll();
}
