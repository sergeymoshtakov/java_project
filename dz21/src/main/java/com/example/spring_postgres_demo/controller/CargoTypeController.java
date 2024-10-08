package com.example.spring_postgres_demo.controller;

import com.example.spring_postgres_demo.dto.CargoTypeDTO;
import com.example.spring_postgres_demo.mapper.CargoTypeMapper;
import com.example.spring_postgres_demo.model.CargoType;
import com.example.spring_postgres_demo.service.cargoType.CargoTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/cargoTypes")
public class CargoTypeController {

    @Autowired
    private CargoTypeService cargoTypeService;

    @GetMapping
    public String getAllCargoTypes(Model model) {
        List<CargoTypeDTO> cargoTypes = cargoTypeService.findAll().stream()
                .map(CargoTypeMapper::toDTO)
                .collect(Collectors.toList());
        model.addAttribute("cargoTypes", cargoTypes);
        return "cargoType/list";
    }

    @GetMapping("/add")
    public String addCargoTypeForm(Model model) {
        model.addAttribute("cargoType", new CargoTypeDTO());
        return "cargoType/add";
    }

    @PostMapping("/add")
    public String saveCargoType(@ModelAttribute CargoTypeDTO cargoTypeDTO) {
        CargoType cargoType = CargoTypeMapper.toEntity(cargoTypeDTO);
        cargoTypeService.save(cargoType);
        return "redirect:/cargoTypes";
    }

    @GetMapping("/update/{id}")
    public String updateCargoTypeForm(@PathVariable int id, Model model) {
        CargoType cargoType = cargoTypeService.findById(id);
        if (cargoType != null) {
            CargoTypeDTO cargoTypeDTO = CargoTypeMapper.toDTO(cargoType);
            model.addAttribute("cargoType", cargoTypeDTO);
            return "cargoType/update";
        }
        return "redirect:/cargoTypes";
    }

    @PostMapping("/update")
    public String updateCargoType(@ModelAttribute CargoTypeDTO cargoTypeDTO) {
        CargoType cargoType = CargoTypeMapper.toEntity(cargoTypeDTO);
        cargoTypeService.update(cargoType);
        return "redirect:/cargoTypes";
    }

    @GetMapping("/delete/{id}")
    public String deleteCargoType(@PathVariable int id) {
        CargoType cargoType = cargoTypeService.findById(id);
        if (cargoType != null) {
            cargoTypeService.delete(cargoType);
        }
        return "redirect:/cargoTypes";
    }
}
