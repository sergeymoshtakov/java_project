package com.example.spring_postgres_demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "statistics")
public class Statistics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="driver_first_name")
    private String driverFirstName;

    @Column(name="driver_last_name")
    private String driverLastName;

    @ManyToOne
    @JoinColumn(name = "destination_id")
    private Destination destination;

    @ManyToOne
    @JoinColumn(name = "cargo_type_id")
    private CargoType cargoType;

    @Column(name="cargo_weight")
    private double cargoWeight;

    @Column(name="income")
    private double income;

    @Column(name="timestamp")
    private Timestamp timestamp;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Statistics that = (Statistics) o;
        return id == that.id
                && Double.compare(that.cargoWeight, cargoWeight) == 0
                && Double.compare(that.income, income) == 0
                && driverFirstName.equals(that.driverFirstName)
                && driverLastName.equals(that.driverLastName)
                && destination.equals(that.destination)
                && cargoType.equals(that.cargoType)
                && timestamp.equals(that.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, driverFirstName, driverLastName, destination, cargoType, cargoWeight, income, timestamp);
    }

    @Override
    public String toString() {
        return String.format("Statistics{id=%d, driverFirstName=%s, driverLastName=%s, destination=%s, cargoType=%s, cargoWeight=%s, income=%s}", id, driverFirstName, driverLastName, destination, cargoType, cargoWeight, income);
    }
}
