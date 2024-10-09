package com.example.spring_postgres_demo.mapper;

import com.example.spring_postgres_demo.dto.DriverDTO;
import com.example.spring_postgres_demo.model.Driver;
import com.example.spring_postgres_demo.model.Status;

public class DriverMapper {

    // Преобразование из Driver в DriverDTO
    public static DriverDTO toDTO(Driver driver) {
        return new DriverDTO(
                driver.getId(),
                driver.getFirstName() + " " + driver.getLastName(),
                driver.getExperience(),
                driver.getStatus().getName().equalsIgnoreCase("Available")
        );
    }

    // Преобразование из DriverDTO в Driver (с указанием статуса)
    public static Driver toEntity(DriverDTO driverDTO, Status status) {
        // Разделение имени на firstName и lastName
        String[] nameParts = driverDTO.getName().split(" ");
        String firstName = nameParts.length > 0 ? nameParts[0] : "";
        String lastName = nameParts.length > 1 ? nameParts[1] : "";

        Driver driver = new Driver();
        driver.setId(driverDTO.getId());
        driver.setFirstName(firstName);
        driver.setLastName(lastName);
        driver.setExperience(driverDTO.getExperience());
        driver.setStatus(status);
        return driver;
    }
}
