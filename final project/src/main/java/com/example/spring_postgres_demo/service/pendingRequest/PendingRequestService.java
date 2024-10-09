package com.example.spring_postgres_demo.service.pendingRequest;

import com.example.spring_postgres_demo.dao.pendingRequest.PendingRequestRepository;
import com.example.spring_postgres_demo.model.Driver;
import com.example.spring_postgres_demo.model.PendingRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PendingRequestService implements IPendingRequestService {

    @Autowired
    private PendingRequestRepository pendingRequestRepository;

    @Override
    public void save(PendingRequest pendingRequest) {
        pendingRequestRepository.save(pendingRequest);
    }

    @Override
    public int[] saveStudentsList(List<PendingRequest> pendingRequests) {
        pendingRequestRepository.saveAll(pendingRequests);
        return pendingRequests.stream().mapToInt(PendingRequest::getId).toArray();
    }

    @Override
    public void update(PendingRequest pendingRequest) {
        if (pendingRequest.getId() != 0) {
            Optional<PendingRequest> existingRequest = pendingRequestRepository.findById(pendingRequest.getId());
            if (existingRequest.isPresent()) {
                pendingRequestRepository.save(pendingRequest);
            }
        }
    }

    @Override
    public void delete(PendingRequest pendingRequest) {
        pendingRequestRepository.delete(pendingRequest);
    }

    @Override
    public List<PendingRequest> findAll() {
        return pendingRequestRepository.findAll();
    }

    @Override
    public void deleteAll() {
        pendingRequestRepository.deleteAll();
    }

    @Override
    public PendingRequest findById(int id) {
        return pendingRequestRepository.findById(id).orElse(null);
    }
}
