package com.example.spring_postgres_demo.factory;

import com.example.spring_postgres_demo.dao.cargoType.CargoTypeRepository;
import com.example.spring_postgres_demo.dao.destination.DestinationRepository;
import com.example.spring_postgres_demo.dao.status.StatusRepository;
import com.example.spring_postgres_demo.model.CargoType;
import com.example.spring_postgres_demo.model.Destination;
import com.example.spring_postgres_demo.model.PendingRequest;
import com.example.spring_postgres_demo.model.Status;
import com.example.spring_postgres_demo.util.RandomElements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Random;

@Service
public class PendingRequestFactory implements IFactory {
    @Autowired
    private CargoTypeRepository cargoTypeRepository;

    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    private DestinationRepository destinationRepository;

    @Override
    public PendingRequest getRandomElement() {
        Random random = new Random();

        Destination randomDestination = RandomElements.getRandomElement(destinationRepository.findAll());
        CargoType randomCargoType = RandomElements.getRandomElement(cargoTypeRepository.findAll());
        Status randomStatus = RandomElements.getRandomElement(statusRepository.findAll());

        double randomCargoWeight = 1000.0 + (5000.0 - 1000.0) * random.nextDouble();

        Timestamp creationTime = Timestamp.from(Instant.now());

        PendingRequest pendingRequest = new PendingRequest();
        pendingRequest.setDestination(randomDestination);
        pendingRequest.setCargoType(randomCargoType);
        pendingRequest.setCargoWeight(randomCargoWeight);
        pendingRequest.setStatus(randomStatus);
        pendingRequest.setCreationTime(creationTime);

        return pendingRequest;
    }
}
