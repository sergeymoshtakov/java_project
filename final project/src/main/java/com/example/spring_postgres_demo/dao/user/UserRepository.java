package com.example.spring_postgres_demo.dao.user;

import com.example.spring_postgres_demo.model.User;
import jakarta.annotation.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Integer> {
    @Nullable
    Optional<User> findById(Integer id);

    User findByUsername(String username);
}
