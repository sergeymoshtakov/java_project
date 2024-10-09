package com.example.spring_postgres_demo.mapper;

import com.example.spring_postgres_demo.dto.PendingRequestDTO;
import com.example.spring_postgres_demo.model.PendingRequest;
import com.example.spring_postgres_demo.service.destination.DestinationService;
import com.example.spring_postgres_demo.service.cargoType.CargoTypeService;
import com.example.spring_postgres_demo.service.status.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component // Make this a Spring-managed component
public class PendingRequestMapper {

    @Autowired
    private DestinationService destinationService;

    @Autowired
    private CargoTypeService cargoTypeService;

    @Autowired
    private StatusService statusService;

    public PendingRequestDTO toDto(PendingRequest pendingRequest) {
        PendingRequestDTO dto = new PendingRequestDTO();
        dto.setId(pendingRequest.getId());
        dto.setDestinationId(pendingRequest.getDestination().getId());
        dto.setCargoTypeId(pendingRequest.getCargoType().getId());
        dto.setCargoWeight(pendingRequest.getCargoWeight());
        dto.setStatusId(pendingRequest.getStatus().getId());
        dto.setCreationTime(pendingRequest.getCreationTime());
        return dto;
    }

    public PendingRequest toEntity(PendingRequestDTO dto) {
        PendingRequest pendingRequest = new PendingRequest();
        pendingRequest.setId(dto.getId());

        // Use the services to retrieve the full entities based on the IDs
        pendingRequest.setDestination(destinationService.findById(dto.getDestinationId())); // Ensure service is available
        pendingRequest.setCargoType(cargoTypeService.findById(dto.getCargoTypeId())); // Ensure service is available
        pendingRequest.setCargoWeight(dto.getCargoWeight());
        pendingRequest.setStatus(statusService.findById(dto.getStatusId())); // Ensure service is available
        pendingRequest.setCreationTime(dto.getCreationTime());

        return pendingRequest;
    }
}
