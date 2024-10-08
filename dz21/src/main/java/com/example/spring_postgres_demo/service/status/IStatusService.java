package com.example.spring_postgres_demo.service.status;

import com.example.spring_postgres_demo.model.Status;

import java.util.List;

public interface IStatusService {
    void save(Status status) ;

    int[] saveStudentsList(List<Status> statuses) ;

    void update(Status status) ;

    void delete(Status status) ;

    List<Status> findAll() ;

    void deleteAll() ;

    Status findById(int id);

    int findIdByStatus(Status status);

    Status findByName(String s);
}
