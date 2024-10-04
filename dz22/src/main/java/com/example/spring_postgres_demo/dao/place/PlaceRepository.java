package com.example.spring_postgres_demo.dao.place;

import com.example.spring_postgres_demo.model.Place;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface PlaceRepository extends JpaRepository<Place, Long> {
    Optional<Place> findById(Long id);

    @Query("SELECT p.id FROM Place p WHERE p.name = :name")
    List<Long> findIdByName(String name); // Изменяем на List<Long>
}
