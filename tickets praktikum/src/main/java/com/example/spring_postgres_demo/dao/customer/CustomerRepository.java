package com.example.spring_postgres_demo.dao.customer;

import com.example.spring_postgres_demo.model.Customer;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findById(Long id);

    @Query("SELECT c.id FROM Customer c WHERE c.name = :name")
    List<Long> findIdByName(String name);
}
