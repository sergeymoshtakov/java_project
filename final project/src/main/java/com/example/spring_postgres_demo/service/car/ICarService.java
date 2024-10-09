package com.example.spring_postgres_demo.service.car;

import com.example.spring_postgres_demo.model.Car;

import java.util.List;

public interface ICarService {
    void save(Car car) ;

    int[] saveStudentsList(List<Car> cars) ;

    void update(Car car) ;

    void delete(Car car) ;

    List<Car> findAll() ;

    void deleteAll() ;

    Car findById(int id);
}
