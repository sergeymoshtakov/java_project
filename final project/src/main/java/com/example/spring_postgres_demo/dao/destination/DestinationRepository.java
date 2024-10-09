package com.example.spring_postgres_demo.dao.destination;

import com.example.spring_postgres_demo.model.Destination;
import jakarta.annotation.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface DestinationRepository extends JpaRepository<Destination, Integer> {
    @Nullable
    Optional<Destination> findById(Integer id);
}
