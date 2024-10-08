package com.example.spring_postgres_demo.controller;

import com.example.spring_postgres_demo.dto.DriverDTO;
import com.example.spring_postgres_demo.mapper.DriverMapper;
import com.example.spring_postgres_demo.model.Driver;
import com.example.spring_postgres_demo.model.Status;
import com.example.spring_postgres_demo.service.driver.DriverService;
import com.example.spring_postgres_demo.service.status.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/drivers")
public class DriverController {

    @Autowired
    private DriverService driverService;

    @Autowired
    private StatusService statusService;

    @GetMapping
    public String getAllDrivers(Model model) {
        List<DriverDTO> drivers = driverService.findAll().stream()
                .map(DriverMapper::toDTO)
                .collect(Collectors.toList());
        model.addAttribute("drivers", drivers);
        return "driver/list";
    }

    @GetMapping("/add")
    public String addDriverForm(Model model) {
        model.addAttribute("driver", new DriverDTO());
        return "driver/add";
    }

    @PostMapping("/add")
    public String saveDriver(@ModelAttribute DriverDTO driverDTO) {
        Status status = statusService.findByName(driverDTO.isAvailable() ? "Available" : "Unavailable");
        Driver driver = DriverMapper.toEntity(driverDTO, status);
        driverService.save(driver);
        return "redirect:/drivers";
    }

    @GetMapping("/update/{id}")
    public String updateDriverForm(@PathVariable int id, Model model) {
        Driver driver = driverService.findById(id);
        if (driver != null) {
            DriverDTO driverDTO = DriverMapper.toDTO(driver);
            model.addAttribute("driver", driverDTO);
            return "driver/update";
        }
        return "redirect:/drivers";
    }

    @PostMapping("/update")
    public String updateDriver(@ModelAttribute DriverDTO driverDTO) {
        Status status = statusService.findByName(driverDTO.isAvailable() ? "Available" : "Unavailable");
        Driver driver = DriverMapper.toEntity(driverDTO, status);
        driverService.update(driver);
        return "redirect:/drivers";
    }

    @GetMapping("/delete/{id}")
    public String deleteDriver(@PathVariable int id) {
        Driver driver = driverService.findById(id);
        if (driver != null) {
            driverService.delete(driver);
        }
        return "redirect:/drivers";
    }
}
