package com.example.spring_postgres_demo.service.statistics;

import com.example.spring_postgres_demo.model.Statistics;

import java.util.List;

public interface IStatisticsService {
    void save(Statistics statistics) ;

    int[] saveStudentsList(List<Statistics> statistics) ;

    void update(Statistics statistics) ;

    void delete(Statistics statistics) ;

    List<Statistics> findAll() ;

    void deleteAll() ;

    Statistics findById(int id);
}
