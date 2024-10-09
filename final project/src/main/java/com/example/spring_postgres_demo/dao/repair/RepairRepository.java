package com.example.spring_postgres_demo.dao.repair;

import com.example.spring_postgres_demo.model.Repair;
import jakarta.annotation.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface RepairRepository extends JpaRepository<Repair, Integer> {
    @Nullable
    Optional<Repair> findById(Integer id);
}
