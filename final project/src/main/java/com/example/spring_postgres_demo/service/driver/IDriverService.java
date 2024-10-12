package com.example.spring_postgres_demo.service.driver;

import com.example.spring_postgres_demo.model.Driver;

import java.util.List;

public interface IDriverService {
    void save(Driver driver) ;

    int[] saveStudentsList(List<Driver> drivers) ;

    void update(Driver driver) ;

    void delete(Driver driver) ;

    List<Driver> findAll() ;

    void deleteAll() ;

    Driver findById(int id);

    List<Driver> findAvailableDrivers();
}
