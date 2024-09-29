package com.example.spring_postgres_demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "requests")
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "destination_id")
    private Destination destination;

    @ManyToOne
    @JoinColumn(name = "cargo_type_id")
    private CargoType cargoType;

    @Column(name="cargo_weight")
    private double cargoWeight;

    @ManyToOne
    @JoinColumn(name = "driver_id")
    private Driver driver;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;

    @Column(name="start_time")
    private Timestamp startTime;

    @Column(name="end_time")
    private Timestamp endTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request request = (Request) o;
        return id == request.id
                && destination == request.destination
                && cargoType == request.cargoType
                && cargoWeight == request.cargoWeight
                && driver == request.driver
                && car == request.car
                && status == request.status
                && startTime == request.startTime
                && endTime == request.endTime;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, destination, cargoType, cargoWeight, car, status, startTime, endTime);
    }

    @Override
    public String toString() {
        return String.format("Request{id=%d, destination='%s', cargoType='%s', cargoWeight=%f, driver='%s', car='%s', status='%s', }", id, destination, cargoType, cargoWeight, driver, car, status);
    }
}
