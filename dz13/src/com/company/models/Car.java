package com.company.models;

public class Car {
    private String name;
    private double maxSpeed;
    public Car(String name, double maxSpeed) {
        this.name = name;
        this.maxSpeed = maxSpeed;
    }
    public String getName() {
        return name;
    }
    public double getMaxSpeed() {
        return maxSpeed;
    }
    public void setMaxSpeed(double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }
    public void setName(String name) {
        this.name = name;
    }
}
