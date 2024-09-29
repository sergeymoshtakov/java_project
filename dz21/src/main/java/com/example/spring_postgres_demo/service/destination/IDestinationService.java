package com.example.spring_postgres_demo.service.destination;

import com.example.spring_postgres_demo.model.Destination;

import java.util.List;

public interface IDestinationService {
    void save(Destination destination) ;

    int[] saveStudentsList(List<Destination> destinations) ;

    void update(Destination destination) ;

    void delete(Destination destination) ;

    List<Destination> findAll() ;

    void deleteAll() ;

    Destination findById(int id);
}
