package com.example.spring_postgres_demo.dao.status;

import com.example.spring_postgres_demo.model.Status;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface StatusRepository extends JpaRepository<Status, Long> {
    Optional<Status> findById(Long id);

    @Query("SELECT s.id FROM Status s WHERE s.name = :name")
    List<Long> findIdByName(String name);
}
