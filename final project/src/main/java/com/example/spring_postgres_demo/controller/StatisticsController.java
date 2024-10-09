package com.example.spring_postgres_demo.controller;

import com.example.spring_postgres_demo.dto.StatisticsDTO;
import com.example.spring_postgres_demo.mapper.StatisticsMapper;
import com.example.spring_postgres_demo.model.Statistics;
import com.example.spring_postgres_demo.service.statistics.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/statistics")
public class StatisticsController {

    @Autowired
    private StatisticsService statisticsService;

    // Получение списка статистики и отображение на представлении
    @GetMapping
    public String getAllStatistics(Model model) {
        List<StatisticsDTO> statisticsList = statisticsService.findAll().stream()
                .map(StatisticsMapper::toDTO)
                .collect(Collectors.toList());
        model.addAttribute("statistics", statisticsList);
        return "statistics/list";
    }
}
