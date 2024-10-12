package com.example.spring_postgres_demo.controller;

import com.example.spring_postgres_demo.dto.PendingRequestDTO;
import com.example.spring_postgres_demo.dto.RequestDTO;
import com.example.spring_postgres_demo.mapper.PendingRequestMapper;
import com.example.spring_postgres_demo.mapper.RequestMapper;
import com.example.spring_postgres_demo.model.CargoType;
import com.example.spring_postgres_demo.model.Destination;
import com.example.spring_postgres_demo.model.PendingRequest;
import com.example.spring_postgres_demo.model.Request;
import com.example.spring_postgres_demo.service.car.CarService;
import com.example.spring_postgres_demo.service.driver.DriverService;
import com.example.spring_postgres_demo.service.pendingRequest.PendingRequestService;
import com.example.spring_postgres_demo.service.destination.DestinationService; // Import service
import com.example.spring_postgres_demo.service.cargoType.CargoTypeService; // Import service
import com.example.spring_postgres_demo.service.request.RequestService;
import com.example.spring_postgres_demo.service.status.StatusService; // Import service
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/pending-requests")
public class PendingRequestController {

    @Autowired
    private PendingRequestService pendingRequestService;

    @Autowired
    private DestinationService destinationService; // Inject service
    @Autowired
    private CargoTypeService cargoTypeService; // Inject service
    @Autowired
    private StatusService statusService; // Inject service

    @Autowired
    private PendingRequestMapper pendingRequestMapper;

    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private RequestService requestService;

    @Autowired
    private CarService carService;

    @Autowired
    private DriverService driverService;

    @GetMapping
    public String listPendingRequests(Model model) {
        List<PendingRequest> pendingRequests = pendingRequestService.findAll();
        model.addAttribute("pendingRequests", pendingRequests);
        return "pending_requests/list"; // Thymeleaf view for listing requests
    }

    @GetMapping("/new")
    public String createPendingRequestForm(Model model) {
        model.addAttribute("pendingRequestDTO", new PendingRequestDTO());
        model.addAttribute("destinations", destinationService.findAll()); // Add destinations to model
        model.addAttribute("cargoTypes", cargoTypeService.findAll()); // Add cargo types to model
        model.addAttribute("statuses", statusService.findAll()); // Add statuses to model
        return "pending_requests/new"; // Thymeleaf view for creating new request
    }

    @PostMapping
    public String createPendingRequest(@ModelAttribute PendingRequestDTO pendingRequestDTO) {
        PendingRequest pendingRequest = pendingRequestMapper.toEntity(pendingRequestDTO);
        pendingRequestService.save(pendingRequest);
        return "redirect:/pending-requests"; // Redirect to the list after saving
    }

    @GetMapping("/{id}/edit")
    public String editPendingRequestForm(@PathVariable int id, Model model) {
        PendingRequest pendingRequest = pendingRequestService.findById(id);
        PendingRequestDTO pendingRequestDTO = pendingRequestMapper.toDto(pendingRequest);
        model.addAttribute("pendingRequestDTO", pendingRequestDTO);
        model.addAttribute("destinations", destinationService.findAll()); // Add destinations to model
        model.addAttribute("cargoTypes", cargoTypeService.findAll()); // Add cargo types to model
        model.addAttribute("statuses", statusService.findAll()); // Add statuses to model
        return "pending_requests/edit"; // Thymeleaf view for editing request
    }

    @PostMapping("/{id}")
    public String updatePendingRequest(@PathVariable int id, @ModelAttribute PendingRequestDTO pendingRequestDTO) {
        PendingRequest pendingRequest = pendingRequestMapper.toEntity(pendingRequestDTO);
        pendingRequest.setId(id); // Ensure the ID is set for the update
        pendingRequestService.update(pendingRequest);
        return "redirect:/pending-requests"; // Redirect to the list after updating
    }

    @GetMapping("/{id}/delete")
    public String deletePendingRequest(@PathVariable int id) {
        pendingRequestService.delete(pendingRequestService.findById(id)); // Assuming you have a delete method in PendingRequestService
        return "redirect:/pending-requests"; // Redirect to the list after deleting
    }

    @GetMapping("/{id}/create-request")
    public String createRequestFromPending(@PathVariable int id, Model model) {
        PendingRequest pendingRequest = pendingRequestService.findById(id);
        PendingRequestDTO pendingRequestDTO = pendingRequestMapper.toDto(pendingRequest);

        // Create a new RequestDTO with initial values from PendingRequest
        RequestDTO requestDTO = new RequestDTO();
        requestDTO.setCargoWeight(pendingRequestDTO.getCargoWeight());

        // Fetch all destinations and cargo types
        List<Destination> allDestinations = destinationService.findAll();
        List<CargoType> allCargoTypes = cargoTypeService.findAll();

        // Filter out the destinations and cargo types already in the pending request
        List<Destination> availableDestinations = allDestinations.stream()
                .filter(destination -> !(destination.getId() == pendingRequestDTO.getDestinationId()))
                .toList();

        List<CargoType> availableCargoTypes = allCargoTypes.stream()
                .filter(cargoType -> !(cargoType.getId() == pendingRequestDTO.getCargoTypeId()))
                .toList();

        model.addAttribute("requestDTO", requestDTO);
        model.addAttribute("drivers", driverService.findAvailableDrivers());
        model.addAttribute("cars", carService.findAvailableCars());
        model.addAttribute("statuses", statusService.findAll());
        model.addAttribute("destinations", availableDestinations); // Filtered destinations
        model.addAttribute("cargoTypes", availableCargoTypes); // Filtered cargo types
        model.addAttribute("pendingRequestId", id); // Ensure ID is passed

        return "pending_requests/new_request"; // Thymeleaf view for creating a new request
    }

    @PostMapping("/{id}/create-request")
    public String createRequest(@PathVariable int id, @ModelAttribute RequestDTO requestDTO) {
        Request request = requestMapper.toEntity(requestDTO);
        requestService.save(request); // Assuming you have a save method in RequestService
        return "redirect:/requests"; // Redirect to the list of requests
    }
}
