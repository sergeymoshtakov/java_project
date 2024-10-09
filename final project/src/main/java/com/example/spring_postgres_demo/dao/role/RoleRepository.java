package com.example.spring_postgres_demo.dao.role;

import com.example.spring_postgres_demo.model.Role;
import jakarta.annotation.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface RoleRepository extends JpaRepository<Role, Integer> {
    @Nullable
    Optional<Role> findById(Integer id);
}
