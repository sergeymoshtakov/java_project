package com.example.spring_postgres_demo.dao.pendingRequest;

import com.example.spring_postgres_demo.model.Driver;
import com.example.spring_postgres_demo.model.PendingRequest;
import jakarta.annotation.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface PendingRequestRepository extends JpaRepository<PendingRequest, Integer> {
    @Nullable
    Optional<PendingRequest> findById(Integer id);
}
