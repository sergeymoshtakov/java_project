package com.example.spring_postgres_demo.controller;

import com.example.spring_postgres_demo.dto.RequestDTO;
import com.example.spring_postgres_demo.mapper.RequestMapper;
import com.example.spring_postgres_demo.model.Request;
import com.example.spring_postgres_demo.service.request.RequestService;
import com.example.spring_postgres_demo.service.destination.DestinationService;
import com.example.spring_postgres_demo.service.cargoType.CargoTypeService;
import com.example.spring_postgres_demo.service.driver.DriverService;
import com.example.spring_postgres_demo.service.car.CarService;
import com.example.spring_postgres_demo.service.status.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/requests")
public class RequestController {

    @Autowired
    private RequestService requestService;

    @Autowired
    private DestinationService destinationService;

    @Autowired
    private CargoTypeService cargoTypeService;

    @Autowired
    private DriverService driverService;

    @Autowired
    private CarService carService;

    @Autowired
    private StatusService statusService;

    @Autowired
    private RequestMapper requestMapper;

    // Отображение списка всех запросов
    @GetMapping
    public String listRequests(Model model) {
        List<RequestDTO> requests = requestService.findAll().stream()
                .map(requestMapper::toDto)
                .collect(Collectors.toList());
        model.addAttribute("requests", requests);
        return "request/list";
    }

    // Отображение формы добавления нового запроса
    @GetMapping("/create")
    public String createRequestForm(Model model) {
        model.addAttribute("requestDTO", new RequestDTO()); // Инициализация RequestDTO
        model.addAttribute("destinations", destinationService.findAll());
        model.addAttribute("cargoTypes", cargoTypeService.findAll());
        model.addAttribute("drivers", driverService.findAll());
        model.addAttribute("cars", carService.findAll());
        model.addAttribute("statuses", statusService.findAll());
        return "request/create";
    }

    // Сохранение нового запроса
    @PostMapping
    public String createRequest(@ModelAttribute RequestDTO requestDTO) {
        Request request = requestMapper.toEntity(requestDTO);
        requestService.save(request);
        return "redirect:/requests";
    }

    // Отображение формы обновления запроса
    @GetMapping("/{id}/edit")
    public String editRequestForm(@PathVariable int id, Model model) {
        Request request = requestService.findById(id);
        if (request != null) {
            RequestDTO requestDTO = requestMapper.toDto(request);
            model.addAttribute("requestDTO", requestDTO);
            model.addAttribute("destinations", destinationService.findAll());
            model.addAttribute("cargoTypes", cargoTypeService.findAll());
            model.addAttribute("drivers", driverService.findAll());
            model.addAttribute("cars", carService.findAll());
            model.addAttribute("statuses", statusService.findAll());
            return "request/edit";
        }
        return "redirect:/requests";
    }

    // Обновление существующего запроса
    @PostMapping("/{id}")
    public String updateRequest(@PathVariable int id, @ModelAttribute RequestDTO requestDTO) {
        Request request = requestMapper.toEntity(requestDTO);
        request.setId(id);
        requestService.update(request);
        return "redirect:/requests";
    }

    // Удаление запроса
    @GetMapping("/{id}/delete")
    public String deleteRequest(@PathVariable int id) {
        requestService.delete(requestService.findById(id));
        return "redirect:/requests";
    }
}
