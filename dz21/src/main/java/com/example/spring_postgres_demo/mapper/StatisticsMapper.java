package com.example.spring_postgres_demo.mapper;

import com.example.spring_postgres_demo.dto.StatisticsDTO;
import com.example.spring_postgres_demo.model.Statistics;

public class StatisticsMapper {

    // Преобразование из Statistics в StatisticsDTO
    public static StatisticsDTO toDTO(Statistics statistics) {
        return new StatisticsDTO(
                statistics.getId(),
                statistics.getDriverFirstName(),
                statistics.getDriverLastName(),
                statistics.getDestination().getName(),
                statistics.getCargoType().getName(),
                statistics.getCargoWeight(),
                statistics.getIncome(),
                statistics.getTimestamp()
        );
    }

    // Преобразование из StatisticsDTO в Statistics
    public static Statistics toEntity(StatisticsDTO statisticsDTO) {
        Statistics statistics = new Statistics();
        statistics.setId(statisticsDTO.getId());
        statistics.setDriverFirstName(statisticsDTO.getDriverFirstName());
        statistics.setDriverLastName(statisticsDTO.getDriverLastName());
        statistics.setCargoWeight(statisticsDTO.getCargoWeight());
        statistics.setIncome(statisticsDTO.getIncome());
        statistics.setTimestamp(statisticsDTO.getTimestamp());
        // Для назначения Destination и CargoType нужно использовать сервисы для поиска сущностей по имени
        return statistics;
    }
}
