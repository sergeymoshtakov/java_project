package com.example.spring_postgres_demo.service.cargoType;

import com.example.spring_postgres_demo.dao.cargoType.CargoTypeRepository;
import com.example.spring_postgres_demo.model.CargoType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CargoTypeService implements ICargoTypeService {

    @Autowired
    private CargoTypeRepository cargoTypeRepository;

    @Override
    public void save(CargoType cargoType) {
        cargoTypeRepository.save(cargoType);
    }

    @Override
    public int[] saveStudentsList(List<CargoType> cargoTypes) {
        cargoTypeRepository.saveAll(cargoTypes);
        return cargoTypes.stream().mapToInt(CargoType::getId).toArray(); // Возвращаем массив ID сохраненных записей
    }

    @Override
    public void update(CargoType cargoType) {
        if (cargoType.getId() != 0) {
            Optional<CargoType> existingCargoType = cargoTypeRepository.findById(cargoType.getId());
            if (existingCargoType.isPresent()) {
                cargoTypeRepository.save(cargoType);
            }
        }
    }

    @Override
    public void delete(CargoType cargoType) {
        cargoTypeRepository.delete(cargoType);
    }

    @Override
    public List<CargoType> findAll() {
        return cargoTypeRepository.findAll();
    }

    @Override
    public void deleteAll() {
        cargoTypeRepository.deleteAll();
    }

    @Override
    public CargoType findById(int id) {
        return cargoTypeRepository.findById(id).orElse(null);
    }
}
