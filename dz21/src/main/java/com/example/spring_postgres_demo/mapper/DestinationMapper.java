package com.example.spring_postgres_demo.mapper;

import com.example.spring_postgres_demo.dto.DestinationDTO;
import com.example.spring_postgres_demo.model.Destination;

public class DestinationMapper {

    // Преобразование из Destination в DestinationDTO
    public static DestinationDTO toDTO(Destination destination) {
        return new DestinationDTO(
                destination.getId(),
                destination.getName()
        );
    }

    // Преобразование из DestinationDTO в Destination
    public static Destination toEntity(DestinationDTO destinationDTO) {
        Destination destination = new Destination();
        destination.setId(destinationDTO.getId());
        destination.setName(destinationDTO.getName());
        return destination;
    }
}
