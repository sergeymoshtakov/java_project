package com.example.spring_postgres_demo.dao.request;

import com.example.spring_postgres_demo.model.Request;
import jakarta.annotation.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface RequestRepository extends JpaRepository<Request, Integer> {
    @Nullable
    Optional<Request> findById(Integer id);
}
