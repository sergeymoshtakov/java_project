package com.example.spring_postgres_demo.mapper;

import com.example.spring_postgres_demo.dto.CargoTypeDTO;
import com.example.spring_postgres_demo.model.CargoType;

public class CargoTypeMapper {

    // Преобразование из CargoType в CargoTypeDTO
    public static CargoTypeDTO toDTO(CargoType cargoType) {
        return new CargoTypeDTO(
                cargoType.getId(),
                cargoType.getName()
        );
    }

    // Преобразование из CargoTypeDTO в CargoType
    public static CargoType toEntity(CargoTypeDTO cargoTypeDTO) {
        CargoType cargoType = new CargoType();
        cargoType.setId(cargoTypeDTO.getId());
        cargoType.setName(cargoTypeDTO.getName());
        return cargoType;
    }
}
