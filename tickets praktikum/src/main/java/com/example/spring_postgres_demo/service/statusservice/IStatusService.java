package com.example.spring_postgres_demo.service.statusservice;

import com.example.spring_postgres_demo.model.Status;

import java.util.List;

public interface IStatusService {
    void save(Status status) ;

    long[] saveStatusesList(List<Status> statuses) ;

    void update(Status status) ;

    void delete(Status status) ;

    List<Status> findAll() ;

    void deleteAll() ;

    Status findById(long id);

    long findIdByStatus(Status status);
}
