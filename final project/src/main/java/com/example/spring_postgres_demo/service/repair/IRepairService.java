package com.example.spring_postgres_demo.service.repair;

import com.example.spring_postgres_demo.model.Repair;

import java.util.List;

public interface IRepairService {
    void save(Repair repair) ;

    int[] saveStudentsList(List<Repair> repairs) ;

    void update(Repair repair) ;

    void delete(Repair repair) ;

    List<Repair> findAll() ;

    void deleteAll() ;

    Repair findById(int id);
}
