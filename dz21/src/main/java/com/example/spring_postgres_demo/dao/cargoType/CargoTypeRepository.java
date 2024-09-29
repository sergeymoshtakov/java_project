package com.example.spring_postgres_demo.dao.cargoType;

import com.example.spring_postgres_demo.model.CargoType;
import jakarta.annotation.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface CargoTypeRepository extends JpaRepository<CargoType, Integer> {
    @Nullable
    Optional<CargoType> findById(Integer id);
}
