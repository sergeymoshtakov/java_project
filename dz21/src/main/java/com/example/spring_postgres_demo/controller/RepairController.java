package com.example.spring_postgres_demo.controller;

import com.example.spring_postgres_demo.dto.RepairDTO;
import com.example.spring_postgres_demo.mapper.RepairMapper;
import com.example.spring_postgres_demo.model.Repair;
import com.example.spring_postgres_demo.service.car.CarService;
import com.example.spring_postgres_demo.service.driver.DriverService;
import com.example.spring_postgres_demo.service.repair.RepairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/repairs")
public class RepairController {

    @Autowired
    private RepairService repairService;

    @Autowired
    private CarService carService;

    @Autowired
    private DriverService driverService;

    // Отображение списка всех ремонтов
    @GetMapping
    public String getAllRepairs(Model model) {
        List<RepairDTO> repairs = repairService.findAll().stream()
                .map(RepairMapper::toDTO)
                .collect(Collectors.toList());
        model.addAttribute("repairs", repairs);
        return "repair/list";
    }

    // Отображение формы добавления нового ремонта
    @GetMapping("/add")
    public String addRepairForm(Model model) {
        model.addAttribute("repair", new RepairDTO());
        model.addAttribute("cars", carService.findAll());
        model.addAttribute("drivers", driverService.findAll());
        return "repair/add";
    }

    // Сохранение нового ремонта
    @PostMapping("/add")
    public String saveRepair(@ModelAttribute RepairDTO repairDTO) {
        Repair repair = RepairMapper.toEntity(repairDTO);
        repair.setCar(carService.findById(repairDTO.getCarId()));
        repair.setDriver(driverService.findById(repairDTO.getDriverId()));
        repairService.save(repair);
        return "redirect:/repairs";
    }


    // Отображение формы обновления ремонта
    @GetMapping("/update/{id}")
    public String updateRepairForm(@PathVariable int id, Model model) {
        Repair repair = repairService.findById(id);
        if (repair != null) {
            RepairDTO repairDTO = RepairMapper.toDTO(repair);
            model.addAttribute("repair", repairDTO);
            model.addAttribute("cars", carService.findAll());
            model.addAttribute("drivers", driverService.findAll());
            return "repair/update";
        }
        return "redirect:/repairs";
    }

    // Обновление существующего ремонта
    @PostMapping("/update")
    public String updateRepair(@ModelAttribute RepairDTO repairDTO) {
        Repair repair = RepairMapper.toEntity(repairDTO);
        repair.setCar(carService.findById(repairDTO.getCarId()));
        repair.setDriver(driverService.findById(repairDTO.getDriverId()));
        repairService.update(repair);
        return "redirect:/repairs";
    }

    // Удаление ремонта
    @GetMapping("/delete/{id}")
    public String deleteRepair(@PathVariable int id) {
        Repair repair = repairService.findById(id);
        if (repair != null) {
            repairService.delete(repair);
        }
        return "redirect:/repairs";
    }
}
