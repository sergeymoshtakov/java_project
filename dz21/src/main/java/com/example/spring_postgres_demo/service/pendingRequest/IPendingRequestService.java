package com.example.spring_postgres_demo.service.pendingRequest;

import com.example.spring_postgres_demo.model.Driver;
import com.example.spring_postgres_demo.model.PendingRequest;

import java.util.List;

public interface IPendingRequestService {
    void save(PendingRequest pendingRequest) ;

    int[] saveStudentsList(List<PendingRequest> pendingRequests) ;

    void update(PendingRequest pendingRequest) ;

    void delete(PendingRequest pendingRequest) ;

    List<PendingRequest> findAll() ;

    void deleteAll() ;

    PendingRequest findById(int id);
}
