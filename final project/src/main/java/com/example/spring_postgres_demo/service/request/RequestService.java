package com.example.spring_postgres_demo.service.request;

import com.example.spring_postgres_demo.dao.request.RequestRepository;
import com.example.spring_postgres_demo.dao.statistics.StatisticsRepository;
import com.example.spring_postgres_demo.model.Request;
import com.example.spring_postgres_demo.model.Statistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class RequestService implements IRequestService {

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private StatisticsRepository statisticsRepository;

    @Override
    public void save(Request request) {
        requestRepository.save(request);

        Statistics statistics = new Statistics();
        statistics.setDriverFirstName(request.getDriver().getFirstName());
        statistics.setDriverLastName(request.getDriver().getLastName());
        statistics.setDestination(request.getDestination());
        statistics.setCargoType(request.getCargoType());
        statistics.setCargoWeight(request.getCargoWeight());
        statistics.setIncome(calculateIncome(request));
        statistics.setTimestamp(new Timestamp(System.currentTimeMillis()));
        statisticsRepository.save(statistics);
    }

    @Override
    public int[] saveStudentsList(List<Request> requests) {
        requestRepository.saveAll(requests);
        requests.forEach(request -> {
            Statistics statistics = new Statistics();
            statistics.setDriverFirstName(request.getDriver().getFirstName());
            statistics.setDriverLastName(request.getDriver().getLastName());
            statistics.setDestination(request.getDestination());
            statistics.setCargoType(request.getCargoType());
            statistics.setCargoWeight(request.getCargoWeight());
            statistics.setIncome(calculateIncome(request)); // расчет дохода
            statistics.setTimestamp(new Timestamp(System.currentTimeMillis())); // текущее время

            statisticsRepository.save(statistics);
        });

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

    private double calculateIncome(Request request) {
        double baseRate = 100;
        return request.getCargoWeight() * baseRate;
    }
}
