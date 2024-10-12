package com.example.spring_postgres_demo.service.driver;

import com.example.spring_postgres_demo.dao.driver.DriverRepository;
import com.example.spring_postgres_demo.enums.Statuses;
import com.example.spring_postgres_demo.model.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DriverService implements IDriverService {

    @Autowired
    private DriverRepository driverRepository;

    @Override
    public void save(Driver driver) {
        driverRepository.save(driver);
    }

    @Override
    public int[] saveStudentsList(List<Driver> drivers) {
        driverRepository.saveAll(drivers);
        return drivers.stream().mapToInt(Driver::getId).toArray();
    }

    @Override
    public void update(Driver driver) {
        if (driver.getId() != 0) {
            Optional<Driver> existingDriver = driverRepository.findById(driver.getId());
            if (existingDriver.isPresent()) {
                driverRepository.save(driver);
            }
        }
    }

    @Override
    public void delete(Driver driver) {
        driverRepository.delete(driver);
    }

    @Override
    public List<Driver> findAll() {
        return driverRepository.findAll();
    }

    @Override
    public void deleteAll() {
        driverRepository.deleteAll();
    }

    @Override
    public Driver findById(int id) {
        return driverRepository.findById(id).orElse(null);
    }

    @Override
    public List<Driver> findAvailableDrivers() {
        return driverRepository.findAll()
                .stream()
                .filter(driver -> Objects.equals(driver.getStatus().getName(), Statuses.AVAILABLE.getName())) // Только те, кто не на рейсе
                .collect(Collectors.toList());
    }
}
