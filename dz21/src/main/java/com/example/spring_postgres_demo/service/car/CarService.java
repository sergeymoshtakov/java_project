package com.example.spring_postgres_demo.service.car;

import com.example.spring_postgres_demo.dao.car.CarRepository;
import com.example.spring_postgres_demo.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService implements ICarService {

    @Autowired
    private CarRepository carRepository;

    @Override
    public void save(Car car) {
        carRepository.save(car);
    }

    @Override
    public int[] saveStudentsList(List<Car> cars) {
        carRepository.saveAll(cars);
        return cars.stream().mapToInt(Car::getId).toArray();
    }

    @Override
    public void update(Car car) {
        if (car.getId() != 0) {
            Optional<Car> existingCar = carRepository.findById(car.getId());
            if (existingCar.isPresent()) {
                carRepository.save(car);
            }
        }
    }

    @Override
    public void delete(Car car) {
        carRepository.delete(car);
    }

    @Override
    public List<Car> findAll() {
        return carRepository.findAll();
    }

    @Override
    public void deleteAll() {
        carRepository.deleteAll();
    }

    @Override
    public Car findById(int id) {
        return carRepository.findById(id).orElse(null);
    }
}
