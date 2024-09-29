package com.example.spring_postgres_demo.factory;

import com.example.spring_postgres_demo.model.CargoType;
import com.example.spring_postgres_demo.util.RandomElements;
import org.springframework.stereotype.Service;

@Service
public class CargoTypeFactory implements IFactory {
    public static final String[] CARGO_TYPES = {
            "Perishable Goods",
            "Electronics",
            "Furniture",
            "Hazardous Materials",
            "Automobiles",
            "Textiles",
            "Metals",
            "Building Materials",
            "Pharmaceuticals",
            "Livestock",
            "Machinery",
            "Wood Products",
            "Plastic Products",
            "Beverages",
            "Food Products"
    };

    @Override
    public CargoType getRandomElement() {
        String name = RandomElements.getRandomElement(CARGO_TYPES);
        CargoType cargoType = new CargoType();
        cargoType.setName(name);
        return cargoType;
    }
}
