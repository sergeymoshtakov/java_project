package com.example.spring_postgres_demo.controller;

import com.example.spring_postgres_demo.dto.CarDTO;
import com.example.spring_postgres_demo.mapper.CarMapper;
import com.example.spring_postgres_demo.model.Car;
import com.example.spring_postgres_demo.model.Status;
import com.example.spring_postgres_demo.service.car.CarService;
import com.example.spring_postgres_demo.service.status.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/cars")
public class CarController {

    @Autowired
    private CarService carService;

    @Autowired
    private StatusService statusService; // Сервис для управления статусами

    @GetMapping
    public String getAllCars(Model model) {
        List<CarDTO> cars = carService.findAll().stream()
                .map(CarMapper::toDTO)
                .collect(Collectors.toList());
        model.addAttribute("cars", cars);
        return "car/list";
    }

    @GetMapping("/add")
    public String addCarForm(Model model) {
        model.addAttribute("car", new CarDTO());
        return "car/add";
    }

    @PostMapping("/add")
    public String saveCar(@ModelAttribute CarDTO carDTO) {
        // Получаем статус на основе доступности
        Status status = statusService.findByName(carDTO.isAvailable() ? "Available" : "Unavailable");
        Car car = CarMapper.toEntity(carDTO, status);
        carService.save(car);
        return "redirect:/cars";
    }

    @GetMapping("/update/{id}")
    public String updateCarForm(@PathVariable int id, Model model) {
        Car car = carService.findById(id);
        if (car != null) {
            CarDTO carDTO = CarMapper.toDTO(car);
            model.addAttribute("car", carDTO);
            return "car/update";
        }
        return "redirect:/cars";
    }

    @PostMapping("/update")
    public String updateCar(@ModelAttribute CarDTO carDTO) {
        // Получаем статус на основе доступности
        Status status = statusService.findByName(carDTO.isAvailable() ? "Available" : "Unavailable");
        Car car = CarMapper.toEntity(carDTO, status);
        carService.update(car);
        return "redirect:/cars";
    }

    @GetMapping("/delete/{id}")
    public String deleteCar(@PathVariable int id) {
        Car car = carService.findById(id);
        if (car != null) {
            carService.delete(car);
        }
        return "redirect:/cars";
    }
}
