package com.example.spring_postgres_demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "pending_requests")
public class PendingRequest {
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
    @JoinColumn(name = "status_id")
    private Status status;

    @Column(name="creation_time")
    private Timestamp creationTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PendingRequest that = (PendingRequest) o;
        return id == that.id
                && destination == that.destination
                && cargoType == that.cargoType
                && cargoWeight == that.cargoWeight
                && status == that.status
                && creationTime == that.creationTime;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, destination, cargoType, cargoWeight, status, creationTime);
    }

    @Override
    public String toString() {
        return String.format("PendingRequest{id=%d, destination=%s, cargoType=%s, cargoWeight=%s, status=%s}", id, destination.getName(), cargoType.getName(), cargoWeight, status.getName());
    }
}
