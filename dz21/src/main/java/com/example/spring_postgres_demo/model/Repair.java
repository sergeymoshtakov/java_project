package com.example.spring_postgres_demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
@Table(name = "repairs")
public class Repair {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    @ManyToOne
    @JoinColumn(name = "driver_id")
    private Driver driver;

    @Column(name="repair_time")
    private Timestamp repairTime;

    @Column(name="is_fixed")
    private boolean isFixed;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Repair repair = (Repair) o;
        return id == repair.id
                && car.equals(repair.car)
                && driver.equals(repair.driver)
                && repairTime.equals(repair.repairTime)
                && isFixed == repair.isFixed;
    }

    @Override
    public String toString() {
        return String.format("Repair{id=%d, car=%s, driver=%s, repairTime=%s, isFixed=%s}", id, car, driver, repairTime, isFixed);
    }
}
