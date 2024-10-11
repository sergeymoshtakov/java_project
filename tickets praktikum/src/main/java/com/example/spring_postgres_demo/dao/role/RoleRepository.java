package com.example.spring_postgres_demo.dao.role;

import com.example.spring_postgres_demo.model.Role;
import io.micrometer.common.lang.Nullable;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Transactional
public interface RoleRepository extends JpaRepository<Role, Long> {
    @Nullable
    Optional<Role> findById(Long id);
}
