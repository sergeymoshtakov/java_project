package com.example.spring_postgres_demo.dao.user;

import com.example.spring_postgres_demo.model.User;
import io.micrometer.common.lang.Nullable;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {
    @Nullable
    Optional<User> findById(Long id);

    User findByUsername(String username);
}
