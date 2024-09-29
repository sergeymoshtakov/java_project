package com.example.spring_postgres_demo.service.statistics;

import com.example.spring_postgres_demo.dao.statistics.StatisticsRepository;
import com.example.spring_postgres_demo.model.Statistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StatisticsService implements IStatisticsService {

    @Autowired
    private StatisticsRepository statisticsRepository;

    @Override
    public void save(Statistics statistics) {
        statisticsRepository.save(statistics);
    }

    @Override
    public int[] saveStudentsList(List<Statistics> statistics) {
        statisticsRepository.saveAll(statistics);
        return statistics.stream().mapToInt(Statistics::getId).toArray();
    }

    @Override
    public void update(Statistics statistics) {
        if (statistics.getId() != 0) {
            Optional<Statistics> existingStatistics = statisticsRepository.findById(statistics.getId());
            if (existingStatistics.isPresent()) {
                statisticsRepository.save(statistics);
            }
        }
    }

    @Override
    public void delete(Statistics statistics) {
        statisticsRepository.delete(statistics);
    }

    @Override
    public List<Statistics> findAll() {
        return statisticsRepository.findAll();
    }

    @Override
    public void deleteAll() {
        statisticsRepository.deleteAll();
    }

    @Override
    public Statistics findById(int id) {
        return statisticsRepository.findById(id).orElse(null);
    }
}
