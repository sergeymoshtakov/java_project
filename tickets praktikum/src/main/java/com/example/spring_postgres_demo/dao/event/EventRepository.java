package com.example.spring_postgres_demo.dao.event;

import com.example.spring_postgres_demo.model.Customer;
import com.example.spring_postgres_demo.model.Event;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface EventRepository extends JpaRepository<Event, Long> {
    Optional<Event> findById(Long id);

    @Query("SELECT s.id FROM Status s WHERE s.name = :name")
    List<Integer> findIdByName(String name);
}
