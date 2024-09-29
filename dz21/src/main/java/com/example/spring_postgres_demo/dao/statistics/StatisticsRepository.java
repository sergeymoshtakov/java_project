package com.example.spring_postgres_demo.dao.statistics;

import jakarta.annotation.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.spring_postgres_demo.model.Statistics;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface StatisticsRepository extends JpaRepository<Statistics, Integer> {
    List<Statistics> findByDriverLastName(String driverLastName);

    @Nullable
    Optional<Statistics> findById(Integer id);
}
