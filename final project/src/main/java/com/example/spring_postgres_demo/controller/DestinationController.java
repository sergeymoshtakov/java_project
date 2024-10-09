package com.example.spring_postgres_demo.controller;

import com.example.spring_postgres_demo.dto.DestinationDTO;
import com.example.spring_postgres_demo.mapper.DestinationMapper;
import com.example.spring_postgres_demo.model.Destination;
import com.example.spring_postgres_demo.service.destination.DestinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/destinations")
public class DestinationController {

    @Autowired
    private DestinationService destinationService;

    // Получение всех пунктов назначения
    @GetMapping
    public String getAllDestinations(Model model) {
        List<DestinationDTO> destinations = destinationService.findAll().stream()
                .map(DestinationMapper::toDTO)
                .collect(Collectors.toList());
        model.addAttribute("destinations", destinations);
        return "destination/list";
    }

    // Переход на форму добавления пункта назначения
    @GetMapping("/add")
    public String addDestinationForm(Model model) {
        model.addAttribute("destination", new DestinationDTO());
        return "destination/add";
    }

    // Сохранение нового пункта назначения
    @PostMapping("/add")
    public String saveDestination(@ModelAttribute DestinationDTO destinationDTO) {
        Destination destination = DestinationMapper.toEntity(destinationDTO);
        destinationService.save(destination);
        return "redirect:/destinations";
    }

    // Переход на форму редактирования пункта назначения
    @GetMapping("/update/{id}")
    public String updateDestinationForm(@PathVariable int id, Model model) {
        Destination destination = destinationService.findById(id);
        if (destination != null) {
            DestinationDTO destinationDTO = DestinationMapper.toDTO(destination);
            model.addAttribute("destination", destinationDTO);
            return "destination/update";
        }
        return "redirect:/destinations";
    }

    // Обновление пункта назначения
    @PostMapping("/update")
    public String updateDestination(@ModelAttribute DestinationDTO destinationDTO) {
        Destination destination = DestinationMapper.toEntity(destinationDTO);
        destinationService.update(destination);
        return "redirect:/destinations";
    }

    // Удаление пункта назначения
    @GetMapping("/delete/{id}")
    public String deleteDestination(@PathVariable int id) {
        Destination destination = destinationService.findById(id);
        if (destination != null) {
            destinationService.delete(destination);
        }
        return "redirect:/destinations";
    }
}
