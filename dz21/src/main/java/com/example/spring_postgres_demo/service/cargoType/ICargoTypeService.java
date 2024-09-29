package com.example.spring_postgres_demo.service.cargoType;

import com.example.spring_postgres_demo.model.CargoType;

import java.util.List;

public interface ICargoTypeService {
    void save(CargoType cargoType) ;

    int[] saveStudentsList(List<CargoType> cargoTypes) ;

    void update(CargoType cargoType) ;

    void delete(CargoType cargoType) ;

    List<CargoType> findAll() ;

    void deleteAll() ;

    CargoType findById(int id);
}
