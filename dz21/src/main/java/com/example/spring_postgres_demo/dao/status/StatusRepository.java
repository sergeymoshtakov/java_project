package com.example.spring_postgres_demo.dao.status;

import com.example.spring_postgres_demo.model.Status;
import jakarta.annotation.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface StatusRepository extends JpaRepository<Status, Integer> {
    Optional<Status> findById(Integer id);

    @Query("SELECT s.id FROM Status s WHERE s.name = ?1")
    int findIdByName(String name);
}
