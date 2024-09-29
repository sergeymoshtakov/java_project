package com.example.spring_postgres_demo.service.request;

import com.example.spring_postgres_demo.dao.request.RequestRepository;
import com.example.spring_postgres_demo.model.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RequestService implements IRequestService {

    @Autowired
    private RequestRepository requestRepository;

    @Override
    public void save(Request request) {
        requestRepository.save(request);
    }

    @Override
    public int[] saveStudentsList(List<Request> requests) {
        requestRepository.saveAll(requests);
        return requests.stream().mapToInt(Request::getId).toArray();
    }

    @Override
    public void update(Request request) {
        if (request.getId() != 0) {
            Optional<Request> existingRequest = requestRepository.findById(request.getId());
            if (existingRequest.isPresent()) {
                requestRepository.save(request);
            }
        }
    }

    @Override
    public void delete(Request request) {
        requestRepository.delete(request);
    }

    @Override
    public List<Request> findAll() {
        return requestRepository.findAll();
    }

    @Override
    public void deleteAll() {
        requestRepository.deleteAll();
    }

    @Override
    public Request findById(int id) {
        return requestRepository.findById(id).orElse(null);
    }
}
