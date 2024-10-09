package com.example.spring_postgres_demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "drivers")
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="experience")
    private int experience;

    @Column(name="salary")
    private double salary;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Driver driver = (Driver) o;
        return id == driver.id
                && experience == driver.experience
                && firstName.equals(driver.firstName)
                && lastName.equals(driver.lastName)
                && Double.compare(driver.salary, salary) == 0
                && status == driver.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id,firstName,lastName,experience,salary,status);
    }

    @Override
    public String toString() {
        return String.format("Driver{id={%d}, first_name='%s', last_name='%s', experience='%s', salary=%f, status='%s'}", id, firstName, lastName, experience, salary, status.getName());
    }
}
