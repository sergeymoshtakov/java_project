package com.example.spring_postgres_demo.service.destination;

import com.example.spring_postgres_demo.dao.destination.DestinationRepository;
import com.example.spring_postgres_demo.model.Destination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DestinationService implements IDestinationService {

    @Autowired
    private DestinationRepository destinationRepository;

    @Override
    public void save(Destination destination) {
        destinationRepository.save(destination);
    }

    @Override
    public int[] saveStudentsList(List<Destination> destinations) {
        destinationRepository.saveAll(destinations);
        return destinations.stream().mapToInt(Destination::getId).toArray();
    }

    @Override
    public void update(Destination destination) {
        if (destination.getId() != 0) {
            Optional<Destination> existingDestination = destinationRepository.findById(destination.getId());
            if (existingDestination.isPresent()) {
                destinationRepository.save(destination);
            }
        }
    }

    @Override
    public void delete(Destination destination) {
        destinationRepository.delete(destination);
    }

    @Override
    public List<Destination> findAll() {
        return destinationRepository.findAll();
    }

    @Override
    public void deleteAll() {
        destinationRepository.deleteAll();
    }

    @Override
    public Destination findById(int id) {
        return destinationRepository.findById(id).orElse(null);
    }

    @Override
    public Destination findByName(String destination) {
        for (Destination destination1 : destinationRepository.findAll()) {
            if (destination1.getName().equals(destination)) {
                return destination1;
            }
        }
        return null;
    }
}
