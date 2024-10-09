package com.example.spring_postgres_demo.mapper;

import com.example.spring_postgres_demo.dto.RepairDTO;
import com.example.spring_postgres_demo.model.Repair;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class RepairMapper {

    // Преобразование из Repair в RepairDTO
    public static RepairDTO toDTO(Repair repair) {
        return new RepairDTO(
                repair.getId(),
                repair.getCar().getId(),
                repair.getCar().getModel(),
                repair.getDriver().getId(),
                repair.getDriver().getFirstName() + " " + repair.getDriver().getLastName(),
                repair.getRepairTime().toLocalDateTime(),  // Преобразование Timestamp в LocalDateTime
                repair.isFixed()
        );
    }

    // Преобразование из RepairDTO в Repair
    public static Repair toEntity(RepairDTO repairDTO) {
        Repair repair = new Repair();
        repair.setId(repairDTO.getId());

        // Преобразование LocalDateTime в Timestamp
        repair.setRepairTime(Timestamp.valueOf(repairDTO.getRepairTime()));
        repair.setFixed(repairDTO.isFixed());
        // Присваиваем Car и Driver сущности
        return repair;
    }
}
