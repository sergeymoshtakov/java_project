package com.example.spring_postgres_demo.dao.car;

import com.example.spring_postgres_demo.model.Car;
import jakarta.annotation.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface CarRepository  extends JpaRepository<Car, Integer> {
    @Nullable
    Optional<Car> findById(Integer id);
}
