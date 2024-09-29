package com.example.spring_postgres_demo.service.request;

import com.example.spring_postgres_demo.model.Request;

import java.util.List;

public interface IRequestService {
    void save(Request request) ;

    int[] saveStudentsList(List<Request> requests) ;

    void update(Request request) ;

    void delete(Request request) ;

    List<Request> findAll() ;

    void deleteAll() ;

    Request findById(int id);
}
