package com.example.spring_postgres_demo.service.status;

import com.example.spring_postgres_demo.dao.status.StatusRepository;
import com.example.spring_postgres_demo.model.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StatusService implements IStatusService {

    private final StatusRepository statusRepository;

    @Autowired
    public StatusService(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    @Override
    public void save(Status status) {
        statusRepository.save(status);
    }

    @Override
    public int[] saveStudentsList(List<Status> statuses) {
        statusRepository.saveAll(statuses);
        return statuses.stream().mapToInt(Status::getId).toArray();
    }

    @Override
    public void update(Status status) {
        if (status.getId() != 0) {
            Optional<Status> existingStatus = statusRepository.findById(status.getId());
            if (existingStatus.isPresent()) {
                statusRepository.save(status);
            }
        }
    }

    @Override
    public void delete(Status status) {
        statusRepository.delete(status);
    }

    @Override
    public List<Status> findAll() {
        return statusRepository.findAll();
    }

    @Override
    public void deleteAll() {
        statusRepository.deleteAll();
    }

    @Override
    public Status findById(int id) {
        return statusRepository.findById(id).orElse(null);
    }

    @Override
    public int findIdByStatus(Status status) {
        return statusRepository.findIdByName(status.getName());
    }
}
