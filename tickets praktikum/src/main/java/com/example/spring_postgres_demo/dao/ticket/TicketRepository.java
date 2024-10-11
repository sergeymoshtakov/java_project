package com.example.spring_postgres_demo.dao.ticket;

import com.example.spring_postgres_demo.model.Event;
import com.example.spring_postgres_demo.model.Ticket;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    Optional<Ticket> findById(Long id);

    List<Ticket> findByEvent(Event event);
}
