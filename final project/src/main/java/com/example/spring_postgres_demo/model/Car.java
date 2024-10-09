package com.example.spring_postgres_demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="model")
    private String model;

    @Column(name="load_capacity")
    private double loadCapacity;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;

    @Column(name="is_broken")
    private boolean isBroken;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return id == car.id
                && model.equals(car.model)
                && loadCapacity == car.loadCapacity
                && status == car.status
                && isBroken == car.isBroken;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, model, loadCapacity, status, isBroken);
    }

    @Override
    public String toString() {
        return String.format("Car{id=%d, model='%s', loadCapacity=%f, status=%s, isBroken=%s}", id, model, loadCapacity, status.getName(), String.valueOf(isBroken));
    }
}
