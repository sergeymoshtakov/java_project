package com.example.spring_postgres_demo.dao.driver;

import com.example.spring_postgres_demo.model.Driver;
import jakarta.annotation.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface DriverRepository extends JpaRepository<Driver, Integer> {
    @Nullable
    Optional<Driver> findById(Integer id);
}
